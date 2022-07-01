(ns dep-vis.neo4j
  (:import (org.neo4j.dbms.api DatabaseManagementServiceBuilder)
           (org.neo4j.configuration.helpers SocketAddress)
           (org.neo4j.configuration.connectors BoltConnector)
           (org.neo4j.configuration GraphDatabaseSettings)))

(defn create-neo4j-database [database-directory]
  (let [management-service-builder (new DatabaseManagementServiceBuilder database-directory)]
    (. management-service-builder setConfig BoltConnector/enabled true)
    (. management-service-builder setConfig BoltConnector/listen_address (new SocketAddress "localhost" 7687))
    (. management-service-builder setConfig GraphDatabaseSettings/auth_enabled false)
    (let [service (. management-service-builder build)]
      (let [graph-db (. service database "neo4j")]
        graph-db))))


;DatabaseManagementService managementService = new DatabaseManagementServiceBuilder( DB_PATH )
;    .setConfig( BoltConnector.enabled, true )
;    .setConfig( BoltConnector.listen_address, new SocketAddress( "localhost", 7687 ) )
;    .build();
;
;



;;management-service/build
;;(let [graph-db (management-service/database "dep-vis")]
;; (management-service/register-shutdown-hook management-service)
;; graph-db)))
