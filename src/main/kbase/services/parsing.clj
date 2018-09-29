(ns kbase.services.parsing
  (:require [net.cgrand.enlive-html :as html]
            [clojurewerkz.urly.core :as urly]
            [clojure.string :as string])
  (:import (java.net URL)
           (java.util UUID)))

(def test-url
  "https://www.nytimes.com/2018/09/18/science/spacex-moon-yusaku-maezawa.html?rref=collection%2Fsectioncollection%2Fscience&action=click&contentCollection=science&region=rank&module=package&version=highlights&contentPlacement=2&pgtype=sectionfront")

(defn fetch-url
  "Grab the contents of the url specified"
  [url]
  (html/html-resource (URL. url)))

(defn og-property->og-content [html og-property]
  (let [meta (-> html
                 (html/select [[:meta (html/attr= :property og-property)]])
                 first
                 :attrs
                 :content)]
    meta))

(defn url->note-db [url]
  (let [html      (fetch-url url)
        meta-tags (html/select html [:meta])
        url       (og-property->og-content meta-tags "og:url")
        title     (og-property->og-content meta-tags "og:title")
        type      (string/lower-case (og-property->og-content meta-tags "og:type"))
        image-url (og-property->og-content meta-tags "og:image")
        desc      (og-property->og-content meta-tags "og:description")
        urly-url  (urly/url-like url)
        domain    (str (urly/protocol-of urly-url)
                       "://"
                       (urly/host-of urly-url))]
    {:db/id            (UUID/randomUUID)
     :note/url         url
     :note/title       title
     :note/description desc
     :note/image-url   image-url
     :note/domain      domain
     :note/type        type
     :note/added       (System/currentTimeMillis)}))
