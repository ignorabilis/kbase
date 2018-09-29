(ns kbase.ui.client.pages.root
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.ui.client.pages.landing :refer [LandingPage]]
            [kbase.ui.client.pages.login.views :refer [LoginPage]]
            [kbase.ui.client.pages.dashboard :refer [Dashboard DashboardPage]]
            [kbase.ui.client.components.user :refer [UserLoad]]
            [kbase.ui.client.pages.login.forms :refer [log-out]]
            [kbase.html5-routing :as routing]
            [fulcro.client.dom :as dom]
            [fulcro.client.primitives :as prim :refer [defsc]]
            [fulcro.client.routing :as r :refer [defrouter]]
            [clojure.string :as string]))

(defrouter TopRouter :top-router
           (ident [this props] [(:ui.pages/by-key props) :single])
           :pages/landing LandingPage
           :pages/login LoginPage
           :pages/dashboard DashboardPage)

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
         :onClick #(routing/nav-to! this :page-handlers/dashboard)}))
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
                   {:root/current-user (prim/get-query UserLoad)}]
   :initial-state (fn [p]
                    (merge
                     routing/app-routing-tree
                     {:root/router (prim/get-initial-state TopRouter {})}))}
  (sui/ui-container
   {:fluid true}
   (ui-menu props)
   (ui-top-router router)))
