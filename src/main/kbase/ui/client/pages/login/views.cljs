(ns kbase.ui.client.pages.login.views
  (:require [kbase.ui.client.pages.login.forms :as forms]
            [fulcro.client.primitives :as prim :refer [defsc]]
            [fulcro.client.mutations :refer [defmutation]]))

(defsc LoginPage [this {:keys [ui.pages/by-key] :as props}]
       {:query         [:ui.pages/by-key]
        :initial-state {:ui.pages/by-key :pages/login}
        :ident         (fn [] [by-key :single])}
       (forms/ui-login-form {}))
