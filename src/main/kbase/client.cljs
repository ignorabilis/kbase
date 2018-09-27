(ns kbase.client
  (:require [kbase.ui.client.pages.root :as root]
            [kbase.ui.client.components.notes :as notes]
            [kbase.html5-routing :as r]
            [fulcro.client :as fc]
            [fulcro.i18n :as i18n]
            [fulcro.client.data-fetch :as df]
            [fulcro.client.primitives :as prim]
            ["intl-messageformat" :as IntlMessageFormat]))

(defn message-format [{:keys [::i18n/localized-format-string ::i18n/locale ::i18n/format-options]}]
  (let [locale-str (name locale)
        formatter  (IntlMessageFormat. localized-format-string locale-str)]
    (.format formatter (clj->js format-options))))

(defonce app (atom nil))

(defn mount []
  (reset! app (fc/mount @app root/Root "app")))

(defn start []
  (mount))


(defn ^:export init []
  (reset! app (fc/new-fulcro-client
               :reconciler-options {:shared      {::i18n/message-formatter message-format}
                                    :render-mode :keyframe ; Good for beginners. Remove to optimize UI refresh
                                    :shared-fn   #(select-keys % [::i18n/current-locale :root/current-user])}
               :started-callback (fn [{:keys [reconciler] :as app}]
                                   (r/start-routing (prim/app-root reconciler)))))
  (start))
