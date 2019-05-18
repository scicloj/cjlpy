(ns cjlpy.hy-test
  (:require [clojure.test :refer :all]
            [cjlpy.hy :refer :all]))


(deftest basic-hy-test

  (is
   (= 3
      (evalhy '(+ 1 2)))))
