(ns kbase.ui.client.components.notes-list
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.ui.client.components.notes :as notes]
            [kbase.api.mutations :as api]
            [fulcro.client.mutations :as m :refer [defmutation]]
            [fulcro.client.dom :as dom]
            [fulcro.client.primitives :refer [defsc]]
            [fulcro.client.cards :refer [defcard-fulcro]]
            [fulcro.client.primitives :as prim]))

(defsc NotesList [this {:keys [db/id user/notes] :as props}]
  {:query [:db/id {:user/notes (prim/get-query notes/NoteItem)}]}
  (let [del-note-fn (fn [note-id]
                      (prim/transact! this `[(api/delete-note {:user-id ~id
                                                               :note-id ~note-id})]))
        tempid      (prim/tempid)]
    (dom/div
     (sui/ui-input
      {:placeholder "Note URL"
       :type        "text"
       :action      {:color   :teal
                     :icon    :plus
                     :onClick (fn []
                                (prim/ptransact!
                                 this
                                 `[(api/add-note {:user-id ~id :tempid ~tempid})
                                   (api/finish-add-note {:user-id ~id})]))}})
     (sui/ui-list
      {:divided true
       :relaxed true}
      (mapv
       (fn [note]
         (notes/ui-note-item (prim/computed note {:onDelete del-note-fn})))
       notes)))))

(def ui-notes-list (prim/factory NotesList))
