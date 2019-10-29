package io.swagger.petstore.api.utils.db.mysql;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import io.swagger.petstore.api.utils.db.mysql.enums.DBNames;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class MSQLBase {
  static Connection connection;
  private static Session session;
  private String DB_USER;
  private String DB_PASSWORD;
  private int lport = 5656;
  private int rport = 3306;
  private String rhost;
  private String DB_URL;

  public MSQLBase() {
    this.DB_USER = "configProperties.mysqlUser()";
    this.DB_PASSWORD = "configProperties.mysqlPass()";
    this.rhost = "configProperties.mysqlHost()";
  }

  public Connection initConnection(DBNames dbName) {
    if (connection == null) {
      this.DB_URL = "jdbc:mysql://localhost:" + lport + "/" + "dbName.getDBName()";
      connectViaSsh();
      loadMysqlDriver();
      log.info("Init MySql connection to {}", DB_URL);
      try {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        log.info("Database connection established");
      } catch (SQLException e) {
        log.error(e.getMessage());
      }
    }

    return connection;
  }

  private void loadMysqlDriver() {
    log.info("Loading driver...");
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      log.info("Driver loaded!");
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("Cannot find the driver in the classpath!", e);
    }
  }

  private void connectViaSsh() {
    JSch jSch = new JSch();
    log.info("Establishing SSH Tunnel to {}:22", "configProperties.mysqlSshAddress()");
    try {
      session =
          jSch.getSession("configProperties.mysqlSshUser()", "configProperties.mysqlSshAddress()", 22);
      session.setConfig("StrictHostKeyChecking", "no");
      log.info("Connecting to remote server");
      jSch.addIdentity("configProperties.mysqlSshPrivateKeyDest()");
      session.connect();
      log.info("Connected");
      int port = session.setPortForwardingL(lport, rhost, rport);
      log.info("localhost:" + port + " -> " + rhost + ":" + rport);
      log.info("Port Forwarded");
    } catch (JSchException e) {
      log.error(e.getMessage());
    }
  }

  public static void closeSession() {
    log.info("Disconnect SSH");
    session.disconnect();
  }

  public static void closeConnection() {
    if (connection != null) {
      log.info("Close db connection");
      try {
        connection.close();
        connection = null;
      } catch (SQLException e) {
        log.error(e.getMessage());
      } catch (NullPointerException ignored) {
      }
      closeSession();
    }
  }
}
