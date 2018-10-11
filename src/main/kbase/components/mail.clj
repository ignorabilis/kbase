(ns kbase.components.mail
  (:require [kbase.services.mail.imap :as imap]
            [taoensso.timbre :as timbre]
            [com.stuartsierra.component :as component]))

(defrecord Mail [mail]
  component/Lifecycle
  (start [component]
    (if mail
      component
      (do
        (timbre/info "Setting up mail manager")
        (let [manager (imap/start-manager)]
          (assoc component :mail manager)))))
  (stop [component]
    (when mail
      (timbre/info "Stopping mail manager")
      (imap/stop-manager mail)
      (dissoc component :mail))))

(defn new-mail
  []
  (map->Mail {}))
