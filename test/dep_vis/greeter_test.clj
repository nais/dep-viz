(ns dep-vis.greeter-test
  (:require [clojure.test :refer :all]
            [dep-vis.greeter :as greeter]))

(deftest subtraction
  (is (= 4 (- 7 3))))

(deftest greeting-for
  (is (= "Hello, world" (greeter/greeting-for ""))))


(deftest greeting-for-goran
  (is (= "Hello, Gøran" (greeter/greeting-for "Gøran"))))