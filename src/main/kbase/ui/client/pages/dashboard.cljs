(ns kbase.ui.client.pages.dashboard
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.ui.client.components.notes :as notes]
            [fulcro.client.primitives :as prim :refer [defsc]]
            [fulcro.client.routing :as r :refer [defrouter]]
            [fulcro.client.dom :as dom]))

(defsc Dashboard [this user]
  {:query [:db/id {:user/notes (prim/get-query notes/NoteItem)}]}
  (sui/ui-grid
   {:columns   2
    :divided   true
    :stretched true
    :stackable true}
   (sui/ui-grid-column
    {:width 4}
    "Tags, Types here")
   (sui/ui-grid-column
    {:width 12}
    (notes/ui-notes user))))

(def ui-dashboard (prim/factory Dashboard))

(defsc DashboardPage [this {:keys [ui.pages/by-key root/current-user] :as props}]
  {:query         [:ui.pages/by-key {[:root/current-user '_] (prim/get-query Dashboard)}]
   :initial-state {:ui.pages/by-key :pages/dashboard}
   :ident         (fn [] [by-key :single])}
  (ui-dashboard current-user))
