(ns kbase.ui.pages.core
  (:require
    [fulcro.server :as server :refer [defquery-root defquery-entity]]
    [fulcro.client.dom :as dom]
    [fulcro.client.mutations :as m :refer [defmutation]]
    [fulcro.client.primitives :as prim :refer [defsc]]
    [fulcro.client.routing :as r :refer [defrouter]]))


(defsc HomePage [this {:keys [screen] :as props}]
       {:query         [:screen]
        :initial-state {:screen :home-page}
        :ident         (fn [] [(:screen props) :single])}
       (dom/div nil "Landing Page"))

(def ui-home-page (prim/factory HomePage))

(defsc Screen2 [this {:keys [screen] :as props}]
       {:query         [:screen]
        :initial-state {:screen :screen2}
        :ident         (fn [] [(:screen props) :single])}
       (dom/div nil "Login screen"))

(def ui-screen-2 (prim/factory Screen2))

(defsc Report [this {:keys [db/id report/name]}]
       {:query [:db/id :kind :report/by-id :report/name]
        :ident [:report/by-id :db/id]}
       (dom/div nil
                (dom/h4 nil name)
                (dom/div nil "Various tables and charts...")))

(defsc Summary [this {:keys [db/id summary/name]}]
       {:query [:db/id :kind :summary/by-id :summary/name]
        :ident [:summary/by-id :db/id]}
       (dom/div nil
                (dom/h4 nil name)
                (dom/div nil "Summary of various things...")))
