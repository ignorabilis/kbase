(ns kbase.ui.client.pages.dashboard
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.ui.client.components.notes :as notes]
            [fulcro.client.primitives :as prim :refer [defsc]]
            [fulcro.client.routing :as r :refer [defrouter]]
            [fulcro.client.dom :as dom]))

(defsc DashboardPage [this props]
  {:query [:db/id {:notes/note-items (prim/get-query notes/NoteItem)}]
   :ident [:notes/by-id :db/id]}
  (dom/div
   (sui/ui-grid
    {:columns   2
     :divided   true
     :stretched true
     :stackable true}
    (sui/ui-grid-column
     {:width 3}
     "Tags, Types here")
    (sui/ui-grid-column
     {:width 13}
     (notes/ui-notes props)))))

(def ui-dashboard (prim/factory DashboardPage))
