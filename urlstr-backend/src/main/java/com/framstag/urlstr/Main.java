package com.framstag.urlstr;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.neo4j.configuration.connectors.BoltConnector;
import org.neo4j.configuration.helpers.SocketAddress;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;
import org.neo4j.graphdb.GraphDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.neo4j.configuration.GraphDatabaseSettings.DEFAULT_DATABASE_NAME;

import java.io.File;

@QuarkusMain
public class Main {
  private static final String DB_DIRECTORY = "db";

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String... args) {
    Quarkus.run(URLStr.class, args);
  }

  private static DatabaseManagementService dbManagementService;

  private static void startNeo4J() {
    File dbDirectory = new File(DB_DIRECTORY);
    dbManagementService = new DatabaseManagementServiceBuilder(dbDirectory)
      .setConfig(BoltConnector.enabled, true)
      .setConfig(BoltConnector.listen_address, new SocketAddress("localhost", 7687))
      .build();
    GraphDatabaseService db = dbManagementService.database(DEFAULT_DATABASE_NAME);
  }

  private static void stopNeo4J() {
    dbManagementService.shutdown();
  }

  public static class URLStr implements QuarkusApplication {
    @Override
    public int run(String... args) {
      log.info("Initialisation...");
      startNeo4J();
      log.info("Initialisation...done");
      Quarkus.waitForExit();
      log.info("Shutdown...");
      stopNeo4J();
      log.info("Shutdown...done");
      return 0;
    }
  }
}
