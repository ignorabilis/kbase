(ns kbase.client
  (:require [kbase.ui.root :as root]
            [kbase.ui.client.pages.dashboard :as dashboard]
            [kbase.ui.client.components.notes :as notes]
            [fulcro.client :as fc]
            [fulcro.i18n :as i18n]
            ["intl-messageformat" :as IntlMessageFormat]
            [fulcro.client.data-fetch :as df]))

(defn message-format [{:keys [::i18n/localized-format-string ::i18n/locale ::i18n/format-options]}]
  (let [locale-str (name locale)
        formatter  (IntlMessageFormat. localized-format-string locale-str)]
    (.format formatter (clj->js format-options))))

(defonce app (atom nil))

(defn mount []
  (reset! app (fc/mount @app dashboard/DashboardPage "app")))

(defn start []
  (mount))

(defn ^:export init []
  (reset! app (fc/new-fulcro-client
               :reconciler-options {:shared    {::i18n/message-formatter message-format}
                                          :render-mode :keyframe ; Good for beginners. Remove to optimize UI refresh
                                          :shared-fn ::i18n/current-locale}
               :started-callback (fn [app]
                                          (df/load app :fetch/notes notes/Notes {:marker false
                                                                           :target [:root/notes]}))))
  (start))
