(ns kbase.api.mutations
  (:require [kbase.api.read :refer [kbase-database]]
            [taoensso.timbre :as timbre]
            [fulcro.server :refer [defmutation]]))

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
