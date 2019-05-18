(ns cjlpy.hy
  (:require [cjlpy.core :refer [dopy evalpy]])
  (:import java.io.File))

(dopy "import hy")

;; TODO: 

(defn evalhy-str
  "Eval hylang code given as a string."
  [code]
  ;; https://hy.readthedocs.io/en/stable/language/interop.html#evaluating-strings-of-hy-code-from-python
  (evalpy (str "hy.eval(hy.read_str('"
               code
               "'))")))

(defn evalhy
  "Eval hylang code given as a clojure form." 
  [form]
  (evalhy-str (pr-str form)))



