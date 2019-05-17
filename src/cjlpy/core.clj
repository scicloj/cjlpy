(ns cjlpy.core
  (:require [clojure.core.async :as async])
  (:import (jep Jep JepConfig NDArray)))

(set! *warn-on-reflection* true)

(defn ->jep []
  (Jep.
   (doto (JepConfig.)
     (.addSharedModules (into-array String ["numpy"])))))


(def at-thread
  "Sending instruction to the same (new) jep object on the same thread.
  See https://github.com/ninia/jep/wiki/How-Jep-Works#threading-complications ."
  (let [in-channel  (async/chan 1)
        out-channel (async/chan 1)]
    (async/thread
      (let [jep ^Jep (->jep)]
        (while true
          (let [[op & args] (async/<!! in-channel)]
            (async/>!! out-channel
                       (-> (try
                             (case op
                               :set  (let [[^String varname value] args]
                                       (.set jep varname value))
                               :get  (let [[varname] args]
                                       (.getValue jep varname))
                               :eval (let [[python-statement] args]
                                       (.eval jep python-statement)))
                             (catch Exception e
                               [:error e]))
                           (or :nil)))))
        (.close jep)))
    (fn [form]
      (async/>!! in-channel form)
      (async/<!! out-channel))))

(defn setpy
  [^String varname value]
  (at-thread
   [:set (name varname) value]))

(defn getpy
  [varname]
  (at-thread
   [:get (name varname)]))

(defn dopy
  [& statements]
  (doseq [statement statements]
    (at-thread
     [:eval statement])))

(defn rand-varname []
  (str "x" (rand-int 99999999)))

(defn assignment [varname expression]
  (str varname "=(" expression ")"))

(defn evalpy
  [& expressions]
  (let [varname (rand-varname)]
    (if (->> expressions
             last
             (assignment varname)
             vector
             (concat (butlast expressions))
             (apply dopy)
             (not= :error))
      (getpy varname)
      :error)))
