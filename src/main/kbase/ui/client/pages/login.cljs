(ns kbase.ui.client.pages.login
  (:require [kbase.ui.semanticui.components :as sui]
            [fulcro.client.dom :as dom]
            [fulcro.client.primitives :as prim :refer [defsc]]))

(defsc LoginPage [this {:keys [ui.pages/by-key] :as props}]
  {:query         [:ui.pages/by-key]
   :initial-state {:ui.pages/by-key :pages/login}
   :ident         (fn [] [by-key :single])}
  (sui/ui-grid
   {:textAlign     :center
    :verticalAlign :middle
    ;; TODO - deal with navbar's height in a more consistent manner
    :style {:height "calc(100vh - 42px)"
            :backgroundColor "#DADADA"}}
   (sui/ui-grid-column
    {:width 6
     :style {:marginTop -200}}
    (sui/ui-header
     {:as "h2"}
     "Login to your account")
    (sui/ui-segment
     {:stacked true}
     (sui/ui-form
      (sui/ui-form-input
       {:placeholder  "Username"
        :icon         "user"
        :iconPosition :left}
       )
      (sui/ui-form-input
       {:placeholder  "Password"
        :icon         "lock"
        :iconPosition :left}
       )
      (sui/ui-form-button
       {:size  :large
        :color :teal
        :fluid true}
       "Login"))))))