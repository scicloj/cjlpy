(ns cjlpy.core
  (:require [clojure.core.async :as async])
  (:import (jep Jep JepConfig NDArray)))

(set! *warn-on-reflection* true)

(defn ->jep []
  (Jep.
   (doto (JepConfig.)
     (.addSharedModules (into-array String ["numpy" "sys"])))))

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
                                       (.set jep (name varname) value))
                               :get  (let [[varname] args]
                                       (.getValue jep (name varname)))
                               :eval (let [[code] args]
                                       (.eval jep code)))
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
   [:set varname value]))

(defn getpy
  [varname]
  (at-thread
   [:get varname]))

(defn dopy
  [& codes]
  (->> codes
       (mapv (fn [code]
               {:code   code
                :result (at-thread
                         [:eval code])}))))

(defn rand-varname []
  (str "x" (rand-int 99999999)))

(defn assignment [varname code]
  (str varname "=(" code ")"))

(defn error? [result]
  (and (vector? result)
       (-> result first (= :error))))

(defn evalpy
  [& codes]
  (let [varname (rand-varname)
        result  (->> codes
                     last
                     (assignment varname)
                     vector
                     (concat (butlast codes))
                     (#(do (println (pr-str %))
                           %))
                     (apply dopy))]
    (if (not (error? result))
      (getpy varname)
      result)))



(defn ndarray [xs]
  (NDArray. (double-array xs)))

(defn ndarray->array [^NDArray a]
  (.getData a))

(def ndarray->vec
  (comp vec ndarray->array))


