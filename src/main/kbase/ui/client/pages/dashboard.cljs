(ns kbase.ui.client.pages.dashboard
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.ui.client.components.notes :as notes]
            [fulcro.client.primitives :as prim :refer [defsc]]
            [fulcro.client.dom :as dom]))

(defsc DashboardPage [this {:keys [root/notes]}]
  {:query [{:root/notes (prim/get-query notes/Notes)}]

   ;; TODO - figure out fulcro css + react components
   :css   [[:.item {:margin "0 !important"}]]
   }
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
     notes))))

(def ui-dashboard (prim/factory DashboardPage))
