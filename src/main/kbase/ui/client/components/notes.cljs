(ns kbase.ui.client.components.notes
  (:require [kbase.ui.semanticui.components :as sui]
            [fulcro.client.mutations :as m :refer [defmutation]]
            [fulcro.client.dom :as dom]
            [fulcro.client.primitives :refer [defsc]]
            [fulcro.client.cards :refer [defcard-fulcro]]
            [fulcro.client.primitives :as prim]
            [clojure.string :as string]))

(defsc NoteItem [this
                 {:keys [db/id note/url
                         note/title note/description note/type note/image-url
                         note/domain]}
                 {:keys [onDelete]}]
  {:query         [:db/id :note/url
                   :note/title :note/description :note/type :note/image-url
                   :note/domain :note/added #_:note/tags]
   :ident         [:note-item/by-id :db/id]
   :initial-state (fn [{:keys [id url
                               title description type image-url
                               domain]}]
                    {:db/id            id
                     :note/url         url
                     :note/title       title
                     :note/description description
                     :note/type        type
                     :note/image-url   image-url
                     :note/domain      domain})}
  (sui/ui-list-item
   (sui/ui-list-content
    (dom/div
     {:style {:float      "right"
              :marginLeft 20}}
     (sui/ui-image
      {:src    image-url
       :width  100
       :height 100}))
    (dom/div
     (sui/ui-list-header
      {:as     "a"
       :target :_blank
       :href   url}
      title)
     (sui/ui-list-description
      {:as "div"}
      (dom/div
       {:style {:minHeight 80}}
       description)
      (dom/div
       (sui/ui-label-group
        (when type
          (sui/ui-label
           {:color (case type
                     "article" :olive
                     "book" :brown
                     "books.book" :brown
                     "video" :blue
                     "video.movie" :blue
                     :grey)
            :style {:marginRight 20}}
           (string/capitalize type)))
        (sui/ui-label
         "Tag 1")
        (sui/ui-label
         "Tag 2")
        (dom/a
         {:href   domain
          :target :_blank}
         domain))
       (sui/ui-button
        {:color   :red
         :basic   true
         :compact true
         :icon    true
         :onClick #(onDelete id)}
        (sui/ui-icon
         {:name "trash"}))))))))

(def ui-note-item (prim/factory NoteItem {:keyfn :db/id}))
