package io.swagger.petstore.api.utils.db.mysql.controllers;

import io.swagger.petstore.api.utils.db.mysql.MySQLMethods;
import io.swagger.petstore.api.utils.db.mysql.enums.DBTables;
import org.awaitility.core.ConditionTimeoutException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.given;

public class AdminMysqlController extends MySQLMethods {

  public String getConfirmationToken(String email) {
    try {
      given()
          .ignoreExceptions()
          .with()
          .pollInterval(1000, MILLISECONDS)
          .await()
          .atMost(3, SECONDS)
          .until(
              () ->
                  !getSmth(
                          DBTables.ADMINS,
                          "confirmation_token",
                          String.format("email='%1$s'", email))
                      .isEmpty());

    } catch (ConditionTimeoutException e) {
      throw new AssertionError("There is no token for user " + email);
    }

    return getSmth(
            DBTables.ADMINS, "confirmation_token", String.format("email='%1$s'", email));
  }

  public void enableAdmin(String email) {
    String flag = "1";
    updateQuery(
            DBTables.ADMINS,
        String.format("enabled='%1$s'", flag),
        String.format("email='%1$s'", email));
  }

  public void disableAdmin(String email) {
    String flag = "0";
    updateQuery(
            DBTables.ADMINS,
        String.format("enabled='%1$s'", flag),
        String.format("email='%1$s'", email));
  }

  public String getAdminId(String email) {
    try {
      given()
          .ignoreExceptions()
          .with()
          .pollInterval(1000, MILLISECONDS)
          .await()
          .atMost(3, SECONDS)
          .until(
              () ->
                  !getSmth(DBTables.ADMINS, "id", String.format("email='%1$s'", email))
                      .isEmpty());

    } catch (ConditionTimeoutException e) {
      throw new AssertionError("There is no id for user " + email);
    }

    return getSmth(DBTables.ADMINS, "id", String.format("email='%1$s'", email));
  }

  public void expirePasswordToken(String email, String date) {
    updateQuery(
            DBTables.ADMINS,
        String.format("password_requested_at='%1$s'", date),
        String.format("email='%1$s'", email));
  }
}
