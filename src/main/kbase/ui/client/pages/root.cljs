(ns kbase.ui.client.pages.root
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.ui.client.pages.landing :refer [LandingPage]]
            [kbase.ui.client.pages.login.views :refer [LoginPage]]
            [kbase.ui.client.pages.dashboard :refer [DashboardPage]]
            [kbase.ui.client.pages.profile.forms :refer [SettingsForm]]
            [kbase.ui.client.pages.login.forms :refer [log-out]]
            [kbase.html5-routing :as routing]
            [fulcro.client.dom :as dom]
            [fulcro.client.primitives :as prim :refer [defsc]]
            [fulcro.client.routing :as r :refer [defrouter]]
            [clojure.string :as string]))

(defrouter DashboardRouter :dashboard-router
           (ident [this props] [:notes/by-id (:db/id props)])
           :notes/by-id DashboardPage)

(def ui-dashboard-router (prim/factory DashboardRouter))

(defsc DashboardHome [this {:keys [notes] :as props}]
  {:query         [:ui.pages/by-key {:notes (prim/get-query DashboardRouter)}]
   :initial-state {:ui.pages/by-key :pages/dashboard
                   :notes           {}}
   :ident         (fn [] [(:ui.pages/by-key props) :single])}
  (ui-dashboard-router notes))

(defrouter TopRouter :top-router
           (ident [this props] [(:ui.pages/by-key props) :single])
           :pages/landing LandingPage
           :pages/login LoginPage
           :pages/dashboard DashboardHome)

(def ui-top-router (prim/factory TopRouter))

(defsc MenuComponent [this {:keys [root/router root/current-user] :as props}]
  (let [current-route (-> router :fulcro.client.routing/current-route :ui.pages/by-key)
        {:keys [db/id user/primary-email user/username]} current-user
        logged-in?    (number? id)]
    (sui/ui-menu
     {:secondary true
      :pointing  true}
     (sui/ui-menu-item
      {:active  (= current-route :pages/landing)
       :onClick #(routing/nav-to! this :page-handlers/landing)}
      "kBase")
     (when logged-in?
       (sui/ui-menu-item
        {:name    :pages/dashboard
         :active  (= current-route :pages/dashboard)
         :onClick #(routing/nav-to! this :page-handlers/dashboard {:id 1})}))
     (sui/ui-menu-menu
      {:position :right}
      (when logged-in?
        (sui/ui-menu-item
         {:name   :pages/login
          :header true}
         (str
          "Hi, "
          (if (string/blank? username)
            primary-email
            username)
          "!")))
      (if logged-in?
        (sui/ui-menu-item
         {:name    :pages/logout
          :onClick #(log-out this)})
        (sui/ui-menu-item
         {:name    :pages/login
          :active  (= current-route :pages/login)
          :onClick #(routing/nav-to! this :page-handlers/login)}))))))

(def ui-menu (prim/factory MenuComponent))

(defsc Root [this {:keys [root/router] :as props}]
  {:query         [{:root/router (prim/get-query TopRouter)}
                   {:root/current-user (prim/get-query SettingsForm)}]
   :initial-state (fn [p]
                    (merge
                     routing/app-routing-tree
                     {:root/router (prim/get-initial-state TopRouter {})}))}
  (sui/ui-container
   {:fluid true}
   (ui-menu props)
   (ui-top-router router)))
