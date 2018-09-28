(ns kbase.api.read
  (:require
    [fulcro.server :refer [defquery-entity defquery-root]]
    [fulcro.i18n :as i18n]
    [taoensso.timbre :as timbre]
    [fulcro.client.primitives :as prim]))

;; Server queries can go here

(defquery-entity :meaning/by-id
                 "Returns the meaning of life."
                 (value [{:keys [query]} id params]
                        (let [meanings {:life       42
                                        :universe   42
                                        :everything 42}]
                          (timbre/info "Thinking about the meaning of " query "...hmmm...")
                          (Thread/sleep 3000)
                          (select-keys meanings query))))

; locale serving from PO files
(defquery-root ::i18n/translations
               (value [env {:keys [locale]}]
                      (if-let [translations (i18n/load-locale "i18n" locale)]
                        translations
                        nil)))

(def kbase-database (atom
                     {:note-items {1 {:db/id            1
                                      :note/url         "https://www.nytimes.com/2018/09/18/science/spacex-moon-yusaku-maezawa.html?rref=collection%2Fsectioncollection%2Fscience&action=click&contentCollection=science&region=rank&module=package&version=highlights&contentPlacement=2&pgtype=sectionfront"
                                      :note/title       "Note A"
                                      :note/description "Description 1"
                                      :note/type        "article"
                                      :note/image-url   (str "https://picsum.photos/100/?image" (rand-int 1000))
                                      :note/domain      "https://www.nytimes.com"}
                                   2 {:db/id            2
                                      :note/url         "https://www.nytimes.com/2018/09/18/science/why-your-dna-is-still-uncharted-territory.html?rref=collection%2Fsectioncollection%2Fscience&action=click&contentCollection=science&region=rank&module=package&version=highlights&contentPlacement=2&pgtype=sectionfront"
                                      :note/title       "Article B"
                                      :note/description "Long description 2 here"
                                      :note/type        "article"
                                      :note/image-url   (str "https://picsum.photos/100/?image" (rand-int 1000))
                                      :note/domain      "https://www.nytimes.com"}
                                   3 {:db/id            3
                                      :note/url         "https://www.nytimes.com/interactive/2018/06/25/science/hayabusa-ryugu-photos.html?rref=collection%2Fsectioncollection%2Fscience&action=click&contentCollection=science&region=rank&module=package&version=highlights&contentPlacement=1&pgtype=sectionfront"
                                      :note/title       "Some other article"
                                      :note/description "Short desc"
                                      :note/type        "article"
                                      :note/image-url   (str "https://picsum.photos/100/?image" (rand-int 1000))
                                      :note/domain      "https://www.nytimes.com"}
                                   4 {:db/id            4
                                      :note/url         "https://www.nytimes.com/2018/09/20/science/nasa-tess-planets.html?rref=collection%2Fsectioncollection%2Fscience&action=click&contentCollection=science&region=rank&module=package&version=highlights&contentPlacement=8&pgtype=sectionfront"
                                      :note/title       "Yet another article"
                                      :note/description "An article desc here"
                                      :note/type        "article"
                                      :note/image-url   (str "https://picsum.photos/100/?image" (rand-int 1000))
                                      :note/domain      "https://www.nytimes.com"}}
                      :users      {1                                 {:db/id              1
                                                                      :user/username      "ignorabilis"
                                                                      :user/password      "l"
                                                                      :user/primary-email "irina.yaroslavova@ignorabilis.com"
                                                                      :user/notes         [[:note-items 1] [:note-items 2] [:note-items 3]]}
                                   2                                 {:db/id              2
                                                                      :user/username      "other"
                                                                      :user/password      "123456"
                                                                      :user/primary-email "irina.yaroslavova@gmail.com"
                                                                      :user/notes         [[:note-items 4]]}
                                   "irina.yaroslavova@ignorabilis.com" [:users 1]
                                   "irina.yaroslavova@gmail.com"       [:users 2]
                                   ;; login shortcut
                                   "a"              [:users 1]}
                      :notes      {1 {:db/id            1
                                      :notes/note-items [[:note-items 1] [:note-items 2] [:note-items 3]]}}}))

(defquery-root
 :fetch/notes
 (value [{:keys [query]} {:keys [id]}]
        (timbre/info :full-query query)
        (let [data   (prim/db->tree
                      [{[:notes 1] query}]
                      @kbase-database
                      @kbase-database)
              result (get data [:notes 1])]
          result)))

;; TODO - this here needs to be hooked to a proper auth library
(defn find-user [query {:keys [user/primary-email user/password]}]
  (let [data              (prim/db->tree
                           [{[:users primary-email] (conj query :user/password)}]
                           @kbase-database
                           @kbase-database)
        user              (get data [:users primary-email])
        ;; TODO - proper hashing/salting/etc of the password.
        password-matches? (= password (:user/password user))
        user              (dissoc user :user/password)]
    (if (and user password-matches?)
      user
      {})))

(defquery-root
 :server.fetch/user
 (value [{:keys [query]} credentials]
        (find-user query credentials)))

(defquery-entity
 :notes/by-id
 (value [{:keys [query]} id params]
        (let [data   (prim/db->tree
                      [{[:notes id] query}]
                      @kbase-database
                      @kbase-database)
              result (get data [:notes id])]
          result)))
