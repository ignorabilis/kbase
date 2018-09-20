(ns kbase.ui.client.components
  (:require
    [fulcro.client.primitives :as prim :refer [defsc]]
    [fulcro.client.dom :as dom]
    ["semantic-ui-react" :refer [Label Dropdown DropdownItem DropdownMenu]]))

(defn ui-dropdown [& args] (dom/macro-create-element Dropdown args))
(defn ui-dropdown-menu [& args] (dom/macro-create-element DropdownMenu args))
(defn ui-dropdown-item [& args] (dom/macro-create-element DropdownItem args))
(defn ui-label [& args] (dom/macro-create-element Label args))

(defsc SomeComponent [this props]
  (dom/div
   (ui-label
    {:color "red"
     :basic true}
    "some text")
   (ui-dropdown
    {:text "Filter"}
    (ui-dropdown-menu
     (ui-dropdown-item {} "A")
     (ui-dropdown-item {} "B")
     (ui-dropdown-item {} "C")))))

(def ui-some-comp (prim/factory SomeComponent))
