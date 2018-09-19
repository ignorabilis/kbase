(ns kbase.intro
  (:require [devcards.core :as rc :refer-macros [defcard]]
            [fulcro.client.cards :refer [defcard-fulcro]]
            [kbase.ui.components :as comp]
            [kbase.ui.client.components :as ccomp]))

(defcard SVGPlaceholder
         "# SVG Placeholder"
         (comp/ui-placeholder {:w 200 :h 200}))

(defcard-fulcro
 SemanticUICard
 "# Semantic UI Test"
 ccomp/SomeComponent
 {}
 {:inspect-data true})
