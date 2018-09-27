(ns kbase.ui.client.pages.profile.forms
  (:require [kbase.ui.semanticui.components :as sui]
            [kbase.api.mutations :as mutations]
            [fulcro.client.dom :as dom]
            [fulcro.client.primitives :as prim :refer [defsc]]
            [fulcro.ui.form-state :as fs]
            [fulcro.client.mutations :refer [defmutation]]
            [fulcro.client.mutations :as m]))

(defsc SettingsForm [this {:keys [db/id user/username user/primary-email] :as props}]
       {:query       [:db/id :user/username :user/primary-email
                      fs/form-config-join]
        :ident       [:user/by-id :db/id]
        :form-fields #{:user/username :user/primary-email}}
       (sui/ui-segment
        (sui/ui-header
         {:as "h2"}
         "Update settings")
        (sui/ui-form
         (sui/ui-form-input
          {:placeholder  "Username"
           :icon         "user"
           :iconPosition :left
           :value        username
           :onBlur       #(prim/transact! this
                                          `[(fs/mark-complete! {:field :user/username})])
           :onChange     #(m/set-string! this :user/username :event %)}))
        (sui/ui-form-button
         {:size    :large
          :color   :teal
          :fluid   true
          :onClick #(prim/transact! this `[(mutations/submit-settings ~(fs/dirty-fields props false))])}
         "Update")))
