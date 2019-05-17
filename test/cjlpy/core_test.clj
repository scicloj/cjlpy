(ns cjlpy.core-test
  (:require [clojure.test :refer :all]
            [cjlpy.core :refer :all]))


(deftest basic-test

  (is
   (do
     (setpy :x 2)
     (= 2 (getpy :x))))

  (is
   (do (dopy "x=3")
       (= 3 (getpy :x))))

  (is
   (= 6 (evalpy "x=2"
                "y=3"
                "x*y"))))


