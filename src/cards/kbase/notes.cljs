(ns kbase.notes
  (:require [kbase.ui.semanticui.components :as sui]
            [fulcro.client.mutations :as m :refer [defmutation]]
            [fulcro.client.dom :as dom]
            [fulcro.client.primitives :refer [defsc]]
            [fulcro.events :as e]
            [fulcro.client.cards :refer [defcard-fulcro]]
            [fulcro.client.primitives :as prim]
            [fulcro.client.data-fetch :as df]))

;; needs tags
(defsc NoteItem [this {:keys [db/id note/url
                              note/title note/description note/type note/image-url
                              note/domain]}]
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
      {:style {:min-height "80px"}}
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
      )))))

(def ui-note-item (prim/factory NoteItem {:keyfn :db/id}))

(defsc Notes [this {:keys [db/id notes/note-items] :as props}]
  {:query         [:db/id {:notes/note-items (prim/get-query NoteItem)}]
   :ident         [:notes/by-id :db/id]}
  (sui/ui-list
   {:divided true
    :relaxed true}
   (mapv ui-note-item note-items)))

(def ui-notes (prim/factory Notes))

(defsc Root [this {:keys [ui/react-key root/notes]}]
  {:query         [:ui/react-key {:root/notes (prim/get-query Notes)}]}
  (dom/div
   #js {:key react-key}
   (ui-notes notes)))

(defcard-fulcro
 note-item-1
 "# A preview for the notes widget."
 Root
 {}
 {:inspect-data true
  :fulcro       {:started-callback (fn [app]
                                     (df/load app :fetch/notes Notes {:marker false
                                                                      :target [:root/notes]}))}})
