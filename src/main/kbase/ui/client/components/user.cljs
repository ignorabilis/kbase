(ns kbase.ui.client.components.user
  (:require [kbase.ui.client.components.notes :as notes]
            [fulcro.client.primitives :as prim :refer [defsc]]))

(defsc UserLoad [this {:keys [db/id user/username user/primary-email user/notes] :as props}]
  {:query       [:db/id :user/username :user/primary-email {:user/notes (prim/get-query notes/NoteItem)}]
   :ident       [:user/by-id :db/id]})