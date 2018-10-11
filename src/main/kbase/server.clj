(ns kbase.server
  (:require
   ; MUST require these, or you won't get them installed.
    [kbase.api.read]
    [kbase.api.mutations]
    [kbase.components.mail :refer [new-mail]]
    [fulcro.easy-server :refer [make-fulcro-server]]))

(defn build-server
  [{:keys [config] :or {config "config/dev.edn"}}]
  (make-fulcro-server
   :parser-injections #{:config}
   :config-path config
   :components {:mail (new-mail)}))
