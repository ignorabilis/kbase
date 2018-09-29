(ns kbase.ui.client.pages.dashboard
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.ui.client.components.notes :as notes]
            [kbase.ui.client.components.notes-list :as notes-list]
            [fulcro.client.primitives :as prim :refer [defsc]]
            [fulcro.client.routing :as r :refer [defrouter]]
            [fulcro.client.dom :as dom]))


(defsc Dashboard [this {:keys [root/notes-filter] :as props}]
  {:query [:db/id {:user/notes (prim/get-query notes/NoteItem)}
           {[:root/notes-filter '_] (prim/get-query notes-list/TypeFilter)}]}
  (sui/ui-grid
   {:columns   2
    :divided   true
    :stretched true
    :stackable true}
   (sui/ui-grid-column
    {:width 4}
    (dom/div
     (notes-list/ui-type-filter notes-filter)))
   (sui/ui-grid-column
    {:width 12}
    (notes-list/ui-notes-list props))))

(def ui-dashboard (prim/factory Dashboard))

(defsc DashboardPage [this {:keys [ui.pages/by-key root/current-user]}]
  {:query         [:ui.pages/by-key {[:root/current-user '_] (prim/get-query Dashboard)}]
   :initial-state {:ui.pages/by-key :pages/dashboard}
   :ident         (fn [] [by-key :single])}
  (dom/div
   {:style {:paddingLeft  20
            :paddingRight 20}}
   (ui-dashboard current-user)))
