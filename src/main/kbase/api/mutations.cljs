(ns kbase.api.mutations
  (:require [kbase.util :as util]
            [kbase.ui.client.components.notes :as notes]
            [fulcro.client.mutations :refer [defmutation]]
            [fulcro.client.logging :as log]
            [fulcro.client.primitives :as prim]
            [fulcro.ui.form-state :as fs]))

(defmutation ping
  "A full-stack mutation for pinging the server. Also shows the ping in the browser log."
  [params]
  (action [env]
          (log/info "Ping!"))
  (remote [env] true))

(defmutation submit-settings [diff]
  (action [{:keys [state]}]
          (swap! state fs/entity->pristine* (util/get-ident diff)))
  (remote [env] true))

(defmutation rerender-root [_]
  (action [{:keys [reconciler] :as env}]
          (prim/force-root-render! reconciler)))

(defmutation delete-note
  [{:keys [user-id note-id]}]
  (action [{:keys [state]}]
          (let [path     [:user/by-id user-id :user/notes]
                old-list (get-in @state path)
                new-list (filterv #(not= (second %) note-id) old-list)]
            (swap! state assoc-in path new-list)))
  (remote [env] true))

(defmutation add-note
  [{:keys [user-id tempid]}]
  (action [{:keys [state]}]
          (let [user-notes-path [:user/by-id user-id :user/notes]
                notes-path      [:note-item/by-id tempid]]
            (swap!
             state
             (fn [state]
               (let [state (assoc-in state notes-path {:db/id tempid})
                     state (update-in state user-notes-path (fn [user-notes]
                                                              (conj user-notes [:note-item/by-id tempid])))]
                 state)))))
  (remote
   [{:keys [ast state]}]
   (fulcro.client.mutations/returning ast @state notes/NoteItem)))

(defmutation finish-add-note
  [all #_{:keys [user-id]}]
  (action [{:keys [state]}]
          ;; TODO - probably show a msg here OR just delete this mutation
          state
          ))

(defmutation filter-notes
  [{:keys [filter-value]}]
  (action [{:keys [state]}]
          (let [path [:root/notes-filter :filter-notes-type/filter-value]]
            (swap! state assoc-in path filter-value))))
