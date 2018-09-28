(ns kbase.ui.client.components.notes
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.api.mutations :as api]
            [fulcro.client.mutations :as m :refer [defmutation]]
            [fulcro.client.dom :as dom]
            [fulcro.client.primitives :refer [defsc]]
            [fulcro.events :as e]
            [fulcro.client.cards :refer [defcard-fulcro]]
            [fulcro.client.primitives :as prim]))

;; needs tags
(defsc NoteItem [this
                 {:keys [db/id note/url
                         note/title note/description note/type note/image-url
                         note/domain]}
                 {:keys [onDelete]}]
  {:query         [:db/id :note/url
                   :note/title :note/description :note/type :note/image-url
                   :note/domain #_:note/tags]
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
   (sui/ui-image
    {:floated "right"
     :src     image-url
     :width   100
     :height  100})
   (sui/ui-list-content
    (sui/ui-list-header
     {:as   "a"
      :href url}
     title)
    (sui/ui-list-description
     {:as "div"}
     (dom/div
      {:style {:minHeight "80px"
               :width     "100%"}}
      description)
     (dom/div
      (sui/ui-label-group
       (sui/ui-label
        "Tag 1")
       (sui/ui-label
        "Tag 2")
       (dom/a
        {:href domain}
        domain))
      (sui/ui-button
       {:color   :red
        :basic   true
        :compact true
        :icon    true
        :onClick #(onDelete id)}
       (sui/ui-icon
        {:name "trash"}))
      )))))

(def ui-note-item (prim/factory NoteItem {:keyfn :db/id}))



(defsc Notes [this {:keys [db/id user/notes] :as props}]
  {:query [:db/id {:user/notes (prim/get-query NoteItem)}]}
  (let [del-note-fn (fn [note-id]
                      (prim/transact! this `[(api/delete-note {:user-id ~id
                                                               :note-id ~note-id})]))]
    (sui/ui-list
     {:divided true
      :relaxed true}
     (mapv
      (fn [note]
        (ui-note-item (prim/computed note {:onDelete del-note-fn})))
      notes))))

(def ui-notes (prim/factory Notes))
