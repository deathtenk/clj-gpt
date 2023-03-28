(ns clj-gpt.client
  (:require [clj-http.client :as http]
            [cheshire.core :as json]
            [clojure.edn :as edn]))

(def model "gpt-3.5-turbo")

(def gpt-endpoint "https://api.openai.com/v1/chat/completions")

(defn- config []
  (edn/read-string (slurp "resources/config.edn")))

(defn generate-response
  ([text]
   (let [cfg (config)]
     (generate-response text cfg)))
  ([text {:keys [api-key]}]
   (let [headers {"content-type" "application/json"
                  "Authorization" (format "Bearer %s" api-key)}
         data {:model model
               :messages [{:role "user" :content text}]}
         {:keys [body]}
         (http/post gpt-endpoint
                    {:form-params data
                     :content-type :json
                     :headers headers})
         {:keys [choices]}
         (json/decode body keyword)]
     choices)))
