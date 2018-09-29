(ns kbase.ui.client.components.notes-list
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.ui.client.components.notes :as notes]
            [kbase.api.mutations :as api]
            [fulcro.client.mutations :as m :refer [defmutation]]
            [fulcro.client.dom :as dom]
            [fulcro.client.primitives :refer [defsc]]
            [fulcro.client.cards :refer [defcard-fulcro]]
            [fulcro.client.primitives :as prim]
            [clojure.string :as string]))

(defsc TypeFilter [this {:keys [filter-notes-type/filter-value]}]
  {:query [:filter-notes-type/filter-value]}
  (sui/ui-dropdown
   {:button      true
    :fluid       true
    :selection   true
    :placeholder "Note Type"
    :value       filter-value
    :onChange    (fn [_ data]
                   (let [{:keys [value]} (js->clj data :keywordize-keys true)]
                     (prim/ptransact!
                      this
                      `[(api/filter-notes {:filter-value ~value})])))
    :options     [{:text  "Article"
                   :value "article"}
                  {:text  "Book"
                   :value "book"}
                  {:text  "Video"
                   :value "video"}
                  {:text  "All"
                   :value "all"}]}))

(def ui-type-filter (prim/factory TypeFilter))

(defn filter-notes-by-type [filter-value {:keys [note/type]}]
  (if (or (nil? filter-value) (= "all" filter-value))
    true
    (string/starts-with? type filter-value)))

(defsc NotesList [this {:keys [db/id user/notes root/notes-filter]}]
  {:query [:db/id {:user/notes (prim/get-query notes/NoteItem)}
           {[:root/notes-filter '_] (prim/get-query TypeFilter)}]}
  (let [{:keys [url]} (prim/get-state this)
        del-note-fn (fn [note-id]
                      (prim/transact! this `[(api/delete-note {:user-id ~id
                                                               :note-id ~note-id})]))
        tempid      (prim/tempid)
        {:keys [filter-notes-type/filter-value]} notes-filter
        notes       (filterv (partial filter-notes-by-type filter-value) notes)
        notes       (sort-by :note/added #(compare %2 %1) notes)]
    (dom/div
     (dom/div
      {:style {:textAlign "right"}}
      (sui/ui-input
       {:placeholder "Add Note URL"
        :type        "text"
        :value       (or url "")
        :style       {:marginBottom 30
                      :marginTop    20}
        :onChange    #(prim/update-state! this assoc :url (.. % -target -value))
        :action      {:color   :teal
                      :icon    :plus
                      :onClick (fn []
                                 (prim/update-state! this assoc :url "")
                                 (prim/ptransact!
                                  this
                                  `[(api/add-note {:user-id ~id
                                                   :tempid  ~tempid
                                                   :url     ~url})
                                    (api/finish-add-note {:user-id ~id})]))}}))
     (sui/ui-list
      {:divided true
       :relaxed true}
      (mapv
       (fn [note]
         (notes/ui-note-item (prim/computed note {:onDelete del-note-fn})))
       notes)))))

(def ui-notes-list (prim/factory NotesList))
