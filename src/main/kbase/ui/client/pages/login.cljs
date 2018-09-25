(ns kbase.ui.client.pages.login
  (:require
    [fulcro.client.dom :as dom]
    [fulcro.client.primitives :as prim :refer [defsc]]))

(defsc LoginPage [this {:keys [screen] :as props}]
  {:query         [:screen]
   :initial-state {:screen :pages/login}
   :ident         (fn [] [(:screen props) :single])}
  (dom/div nil "Login screen"))