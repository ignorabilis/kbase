(ns kbase.api.mutations
  (:require [kbase.api.read :refer [kbase-database]]
            [taoensso.timbre :as timbre]
            [fulcro.server :refer [defmutation]]
            [fulcro.client.primitives :as prim])
  (:import (java.util UUID)))

;; Place your server mutations here

(defmutation ping
             "Server mutation for ping, which just prints incoming parameters to the server log."
             [params]
             (action [env]
                     (timbre/info "Deep Thought giggles at your simple parameters: " params)))

(defmutation delete-note
             [{:keys [user-id note-id]}]
             (action [{:keys [state]}]
                     (swap! kbase-database update-in [:note-items] (fn [note-items]
                                                                     (dissoc note-items note-id)))
                     (swap! kbase-database update-in [:users user-id :user/notes] (fn [users-notes]
                                                                                    (filterv #(not= (second %) note-id) users-notes)))))

(defmutation add-note
             [{:keys [user-id tempid]}]
             (action [{:keys [state]}]
                     (let [id (UUID/randomUUID)]
                       {::prim/tempids    {tempid id}
                        :db/id            id
                        :note/url         "https://www.nytimes.com/2018/09/18/science/spacex-moon-yusaku-maezawa.html?rref=collection%2Fsectioncollection%2Fscience&action=click&contentCollection=science&region=rank&module=package&version=highlights&contentPlacement=2&pgtype=sectionfront"
                        :note/title       "Added Note A"
                        :note/description "Added from server"
                        :note/type        "article"
                        :note/image-url   (str "https://picsum.photos/100/?image" (rand-int 1000))
                        :note/domain      "https://www.nytimes.com"})))
