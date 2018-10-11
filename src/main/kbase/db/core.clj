(ns kbase.db.core
  (:require [kbase.api.read :refer [kbase-database]]
            [kbase.services.web.scrape :as web.scrape]))

(defn find-user [primary-email]
  (let [[_ user-id] (get-in @kbase-database [:users primary-email])]
    user-id))

(defn write-note [user-id url]
  (let [{:keys [db/id] :as note} (web.scrape/url->note-db url)]
    (swap!
     kbase-database
     update-in
     [:note-items]
     (fn [note-items]
       (assoc note-items id note)))

    (swap!
     kbase-database
     update-in
     [:users user-id :user/notes]
     (fn [users-notes]
       (conj users-notes [:note-items id])))

    note))
