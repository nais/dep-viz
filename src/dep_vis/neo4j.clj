(ns dep-vis.neo4j
  (:import (org.neo4j.dbms.api DatabaseManagementServiceBuilder)))

(defn create-neo4j-database [database-directory]
  (let [management-service-builder (new DatabaseManagementServiceBuilder database-directory)]
    (let [service (. management-service-builder build)]
      (let [graph-db (. service database "neo4j")]
        graph-db))))


;;management-service/build
;;(let [graph-db (management-service/database "dep-vis")]
;; (management-service/register-shutdown-hook management-service)
;; graph-db)))
