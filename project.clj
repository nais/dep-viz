(defproject dep-vis "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [io.pedestal/pedestal.service "0.5.10"]
                 [io.pedestal/pedestal.jetty "0.5.10"]
                 [org.neo4j/neo4j "4.4.8" :exclusions [org.slf4j/slf4j-nop]]
                 [org.neo4j/neo4j-bolt "4.4.8" :exclusions [org.slf4j/slf4j-nop]]
                 [ch.qos.logback/logback-classic "1.2.11" :exclusions [org.slf4j/slf4j-api]]
                 ]
  :min-lein-version "2.0.0"
  :resource-paths ["config", "resources"]
  ;; If you use HTTP/2 or ALPN, use the java-agent to pull in the correct alpn-boot dependency
  ;:java-agents [[org.mortbay.jetty.alpn/jetty-alpn-agent "2.0.5"]]
  :profiles {:dev     {:aliases      {"run-dev" ["trampoline" "run" "-m" "dep-vis.server/run-dev"]}
                       :dependencies [[io.pedestal/pedestal.service-tools "0.5.10"]]}
             :uberjar {:aot [dep-vis.server]}}
  :main ^{:skip-aot true} dep-vis.server)
