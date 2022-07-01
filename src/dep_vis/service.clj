(ns dep-vis.service
  (:require [dep-vis.greeter :as greeter]
            [dep-vis.neo4j :as neo4j]
            [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route :as route]
            [neo4j-clj.core :as db]
            [ring.util.response :as ring-resp]))

(defn about-page
  [request]
  (ring-resp/response (format "Clojure %s - served from %s"
                              (clojure-version)
                              (route/url-for ::about-page))))

(defn home-page
  [request]
  (ring-resp/response "Hello World!"))

(def instance
  (neo4j/create-neo4j-database))

;(db/defquery create-user
 ;            "CREATE (u:user $user)")

(defn populate-DB [request]
  [request]
;  (with-open [session (db/get-session instance)]
;    (create-user session {:user {:first-name "Luke" :last-name "Skywalker"}}))
(ring-resp/response "Hello database!") )



(defn respond-hello [request]
  (let [nm (get-in request [:query-params :name])
        resp (greeter/greeting-for nm)]
    {:status 200 :body resp}))

;; Defines "/" and "/about" routes with their associated :get handlers.
;; The interceptors defined after the verb map (e.g., {:get home-page}
;; apply to / and its children (/about).
(def common-interceptors [(body-params/body-params) http/html-body])

(def routes (route/expand-routes
              #{["/" :get (conj common-interceptors `home-page)]
                ["/about" :get (conj common-interceptors `about-page)]
                ["/greet" :get respond-hello :route-name :greet]
                ["/populateDB" :get populate-DB :route-name :populateDB]
                })
  )



(def service {:env          :prod
              ::http/routes routes
              ::http/type   :jetty
              ::http/port   8080
              })

