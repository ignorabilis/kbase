(ns kbase.ui.client.pages.landing
  (:require
    [fulcro.client.dom :as dom]
    [fulcro.client.primitives :as prim :refer [defsc]]))

(defsc LandingPage [this {:keys [screen] :as props}]
  {:query         [:screen]
   :initial-state {:screen :pages/landing}
   :ident         (fn [] [(:screen props) :single])}
  (dom/div nil "Landing Page"))
