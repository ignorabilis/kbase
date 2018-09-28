(ns kbase.ui.client.pages.login.forms
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.api.mutations :as mutations]
            [kbase.ui.client.components.user :as user]
            [kbase.html5-routing :as routing]
            [fulcro.client.dom :as dom]
            [fulcro.client.primitives :as prim :refer [defsc]]
            [fulcro.client.data-fetch :as df]
            [fulcro.client.mutations :refer [defmutation]]
            [clojure.string :as string]))

(defmutation log-in [credentials]
  (action [{:keys [state] :as env}]
          (df/load-action env :server.fetch/user user/UserLoad
                          {:params        credentials
                           :without       #{:fulcro.ui.form-state/config :user/password}
                           :post-mutation `mutations/rerender-root
                           :target        [:root/current-user]}))
  (remote [env]
          (df/remote-load env)))

(defsc UserTinyPreview [this props]
  {:query         [:db/id :user/username :user/primary-email]
   :initial-state (fn [params] {:db/id :guest})
   :ident         [:user/by-id :db/id]})

(defmutation log-out-m [_]
  (action [{:keys [state] :as env}]
          (df/load-action env :server.fetch/user UserTinyPreview
                          {:params        {:logout true}
                           :post-mutation `mutations/rerender-root
                           :target        [:root/current-user]}))
  (remote [env]
          (df/remote-load env)))

(defn log-out [component]
  (prim/transact! component `[(log-out-m nil)])
  (routing/nav-to! component :page-handlers/login))

(defsc LoginForm [this props]
  (let [{:keys [user/primary-email user/password]
         :as   credentials} (prim/get-state this)
        {:keys [user/username] :as current-user} (prim/shared this :root/current-user)
        logged-in? (number? (:db/id current-user))]
    (sui/ui-grid
     {:textAlign     :center
      :verticalAlign :middle
      ;; TODO - deal with navbar's height in a more consistent manner
      :style         {:height          "calc(100vh - 42px)"
                      :backgroundColor "#DADADA"}}
     (sui/ui-grid-column
      {:width 6
       :style {:marginTop -200}}
      (if logged-in?
        (sui/ui-header
         {:as    "h2"
          :color :teal}
         (str
          "Welcome, "
          (if (string/blank? username)
            primary-email
            username)
          "!"))
        (dom/div
         (sui/ui-header
          {:as    "h2"
           :color :teal}
          "Login to your account")
         (sui/ui-segment
          {:stacked true}
          (sui/ui-form
           (sui/ui-form-input
            {:placeholder  "Email"
             :icon         "user"
             :iconPosition :left
             :value        (or primary-email "")
             :onChange     #(prim/update-state! this assoc :user/primary-email (.. % -target -value))})
           (sui/ui-form-input
            {:placeholder  "Password"
             :type         "password"
             :icon         "lock"
             :iconPosition :left
             :value        (or password "")
             :onChange     #(prim/update-state! this assoc :user/password (.. % -target -value))})
           (sui/ui-form-button
            {:size    :large
             :color   :teal
             :fluid   true
             :onClick #(prim/transact! this `[(log-in ~credentials)])}
            "Login")))))))))

(def ui-login-form (prim/factory LoginForm))
