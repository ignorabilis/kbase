(ns kbase.ui.client.pages.landing
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.html5-routing :as routing]
            [fulcro.client.dom :as dom]
            [fulcro.client.primitives :as prim :refer [defsc]]))

(defsc LandingPage [this {:keys [ui.pages/by-key]}]
  {:query         [:ui.pages/by-key]
   :initial-state {:ui.pages/by-key :pages/landing}
   :ident         (fn [] [by-key :single])}
  (sui/ui-grid
   {:columns 16
    :verticalAlign :middle
    ;; TODO - deal with navbar's height in a more consistent manner
    :style {:height "calc(100vh - 42px)"}}
   (sui/ui-grid-column
    {:width 16
     :style {:paddingTop 0}}
    ;; TODO - vertical-align middle not working for some reason
    (sui/ui-segment
     {:inverted  true
      :textAlign :center
      :style     {:minHeight    500
                  :borderRadius 0}}
     (sui/ui-header
      {:as "h1"}
      "kBase")
     (sui/ui-header
      {:as "h4"}
      "Imagine anything")
     (sui/ui-button
      {:size    :large
       :color   :blue
       :onClick #(routing/nav-to! this :page-handlers/login)}
      "Get Started"
      (sui/ui-icon
       {:name "right arrow"}))))))
