package io.swagger.petstore.api.utils.db.mysql.controllers;

import io.swagger.petstore.api.utils.db.mysql.MySQLMethods;
import io.swagger.petstore.api.utils.db.mysql.enums.DBTables;
import org.awaitility.core.ConditionTimeoutException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.given;

public class AdminMysqlController extends MySQLMethods {

  public static final String EMAIL = "email='%1$s'";
  public static final String ENABLED = "enabled='%1$s'";
  public static final String ID = "id";
  public static final String CONFIRMATION_TOKEN = "confirmation_token";
  public static final String PASSWORD_REQUESTED_AT = "password_requested_at='%1$s'";

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
                  !getSmth(DBTables.ADMINS, CONFIRMATION_TOKEN, String.format(EMAIL, email))
                      .isEmpty());

    } catch (ConditionTimeoutException e) {
      throw new AssertionError("There is no token for user " + email);
    }

    return getSmth(DBTables.ADMINS, CONFIRMATION_TOKEN, String.format(EMAIL, email));
  }

  public void enableAdmin(String email) {
    String flag = "1";
    updateQuery(DBTables.ADMINS, String.format(ENABLED, flag), String.format(EMAIL, email));
  }

  public void disableAdmin(String email) {
    String flag = "0";
    updateQuery(DBTables.ADMINS, String.format(ENABLED, flag), String.format(EMAIL, email));
  }

  public String getAdminId(String email) {
    try {
      given()
          .ignoreExceptions()
          .with()
          .pollInterval(1000, MILLISECONDS)
          .await()
          .atMost(3, SECONDS)
          .until(() -> !getSmth(DBTables.ADMINS, ID, String.format(EMAIL, email)).isEmpty());

    } catch (ConditionTimeoutException e) {
      throw new AssertionError("There is no id for user " + email);
    }

    return getSmth(DBTables.ADMINS, ID, String.format(EMAIL, email));
  }

  public void expirePasswordToken(String email, String date) {
    updateQuery(
        DBTables.ADMINS, String.format(PASSWORD_REQUESTED_AT, date), String.format(EMAIL, email));
  }
}
