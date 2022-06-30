(ns dep-vis.greeter)

(defn greeting-for [nm]
  (cond
    (empty? nm)         "Hello, world"
    :else               (str "Hello, " nm )))
