(ns kbase.services.mail.imap
  (:require [kbase.services.mail.parsing :as parsing]
            [kbase.db.core :as db]
            [clojure-mail.core :refer :all]
            [clojure-mail.gmail :as gmail]
            [clojure-mail.message :refer (read-message)]
            [clojure-mail.events :as events]))

;; for this to work go to https://www.google.com/settings/security/lesssecureapps
;; and allow less secure apps, i.e. external apps like this one
(def creds {:username "owsy.test@gmail.com"
            :password "OwsyTest1"})

(def msg-to-pull)

;; TODO - this needs to be part of some state handling lib;
;; in the case of this project - component
(defn gstore [{:keys [username password]}]
  (gmail/store username password))

(defn inbox-messages [store]
  (inbox store))

(defn latest-message [inbox]
  (read-message (first inbox)))

(defn write-to-db [[user entries]]
  (let [user-id (db/find-user user)]
    (mapv #(db/write-note user-id (first %)) entries)))

(defn message-added [{:keys [messages]}]
  (mapv
   #(-> %
        read-message
        parsing/parse-mail
        write-to-db)
   messages))

(defn message-removed [e]
  ;; not used
  )

;; Create a manager and start listening to the inbox, printing the subject of new messages
(defn start-manager []
  (let [{:keys [username password]} creds
        s      (get-session "imaps")
        gstore (store "imaps" s "imap.gmail.com" username password)
        folder (open-folder gstore "inbox" :readonly)
        im     (events/new-idle-manager s)]
    (events/add-message-count-listener
     message-added
     message-removed
     folder
     im)
    im))

(defn stop-manager [manager]
  (when manager
    (events/stop manager)))
