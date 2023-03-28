(ns clj-gpt.client-test
  (:require [clojure.test :refer :all]
            [clj-gpt.client :as chat]
            [clojure.edn :as edn]))

(defn- config []
       (edn/read-string (slurp "resources/config.edn")))

(deftest test-generate-response
  (let [response (chat/generate-response "Hello, how are you?" (config))]
    (is (and (vector? response)
             (not-empty (-> response
                            first
                            :message
                            :content))))))

(deftest test-unauthorized
  (try
    (chat/generate-response "Hello, how are you?" {:api-key "invalid-api-key"})
    (catch Exception e
      (let [{:keys [status]} (ex-data e)]
        (= status 401)))))
