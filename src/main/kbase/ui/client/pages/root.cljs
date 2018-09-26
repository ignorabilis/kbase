(ns kbase.ui.client.pages.root
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.ui.client.pages.landing :refer [LandingPage]]
            [kbase.ui.client.pages.login :refer [LoginPage]]
            [kbase.ui.client.pages.dashboard :refer [DashboardPage]]
            [kbase.html5-routing :as routing]
            [fulcro.client.dom :as dom]
            [fulcro.client.primitives :as prim :refer [defsc]]
            [fulcro.client.routing :as r :refer [defrouter]]))

(defrouter DashboardRouter :dashboard-router
           (ident [this props] [:notes/by-id (:db/id props)])
           :notes/by-id DashboardPage)

(def ui-report-router (prim/factory DashboardRouter))

(defsc DashboardHome [this {:keys [notes] :as props}]
  {:query         [:ui.pages/by-key {:notes (prim/get-query DashboardRouter)}]
   :initial-state {:ui.pages/by-key :pages/dashboard
                   :notes           {}}
   :ident         (fn [] [(:ui.pages/by-key props) :single])}
  (ui-report-router notes))

(defrouter TopRouter :top-router
           (ident [this props] [(:ui.pages/by-key props) :single])
           :pages/landing LandingPage
           :pages/login LoginPage
           :pages/dashboard DashboardHome)

(def ui-top-router (prim/factory TopRouter))

(defsc Root [this {:keys [root/router]}]
  {:query         [{:root/router (prim/get-query TopRouter)}]
   :initial-state (fn [p]
                    (merge
                     routing/app-routing-tree
                     {:root/router (prim/get-initial-state TopRouter {})}))}
  (let [current-route (-> router :fulcro.client.routing/current-route :ui.pages/by-key)]
    (sui/ui-container
     {:fluid true}
     (sui/ui-menu
      (sui/ui-menu-item
       {:name    :pages/landing
        :active  (= current-route :pages/landing)
        :onClick #(routing/nav-to! this :page-handlers/landing)})
      (sui/ui-menu-item
       {:name    :pages/dashboard
        :active  (= current-route :pages/dashboard)
        :onClick #(routing/nav-to! this :page-handlers/dashboard {:id 1})})
      (sui/ui-menu-menu
       {:position :right}
       (sui/ui-menu-item
        {:name    :pages/login
         :active  (= current-route :pages/login)
         :onClick #(routing/nav-to! this :page-handlers/login)})))
     (ui-top-router router))))
