(ns kbase.api.mutations
  (:require [kbase.util :as util]
            [fulcro.client.mutations :refer [defmutation]]
            [fulcro.client.logging :as log]
            [fulcro.client.primitives :as prim]
            [fulcro.ui.form-state :as fs]))

;; Place your client mutations here

(defmutation ping
  "A full-stack mutation for pinging the server. Also shows the ping in the browser log."
  [params]
  (action [env]
          (log/info "Ping!"))
  (remote [env] true))

(defmutation submit-settings [diff]
  (action [{:keys [state]}]
          (swap! state fs/entity->pristine* (util/get-ident diff)))
  (remote [env] true))

(defmutation rerender-root [_]
  (action [{:keys [reconciler] :as env}]
          (prim/force-root-render! reconciler)))
