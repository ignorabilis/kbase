(ns kbase.ui.client.pages.dashboard
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.ui.client.components.notes :as notes]
            [fulcro.client.primitives :as prim :refer [defsc]]
            [fulcro.client.routing :as r :refer [defrouter]]
            [fulcro.client.dom :as dom]))

(defsc DashboardPage [this {:keys [db/id notes/note-items] :as props}]
  {:query         [:screen :db/id {:notes/note-items (prim/get-query notes/Notes)}]
   :initial-state {:screen :pages/dashboard
                   :notes/note-items {}}
   :ident         (fn [] [(:screen props) :single])}
  (dom/div
   (prn "some notes????" props)
   (dom/h4 "the page")
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
     (notes/ui-notes
      note-items)))))

#_(defsc DashboardPage [this {:keys [screen] :as props}]
    {:query         [:screen]
     :initial-state {:screen :pages/dashboard}
     :ident         (fn [] [(:screen props) :single])}
    (ui-dashboard))
