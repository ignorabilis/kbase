(ns kbase.ui.client.pages.root
  (:require
    [kbase.ui.client.pages.landing :refer [LandingPage]]
    [kbase.ui.client.pages.login :refer [LoginPage]]
    [kbase.ui.client.pages.dashboard :refer [DashboardPage]]
    [kbase.html5-routing :as routing]
    [fulcro.client.dom :as dom]
    [fulcro.client.primitives :as prim :refer [defsc]]
    [fulcro.client.routing :as r :refer [defrouter]]))

(defrouter TopRouter :top-router
           (ident [this props] [(:screen props) :single])
           :pages/landing LandingPage
           :pages/login LoginPage
           :pages/dashboard DashboardPage)

(def ui-top-router (prim/factory TopRouter))

(defsc Root [this {:keys [root/router]}]
  {:query         [{:root/router (prim/get-query TopRouter)}]

   ;; TODO - figure out fulcro css + react components
   :css           [[:.item {:margin "0 !important"}]]
   :initial-state (fn [p]
                    (merge
                     routing/app-routing-tree
                     {:root/router (prim/get-initial-state TopRouter {})}))}

  ;;TODO - this should be a semantic ui nav
  (dom/div nil
           (dom/button #js {:onClick #(routing/nav-to! this :page-handlers/landing)} "Landing")
           (dom/button #js {:onClick #(routing/nav-to! this :page-handlers/login)} "Login")
           (dom/button #js {:onClick #(routing/nav-to! this :page-handlers/dashboard)} "Dashboard")
           (ui-top-router router)))
