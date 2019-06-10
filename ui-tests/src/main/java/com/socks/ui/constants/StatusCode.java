package com.socks.ui.constants;

import static org.apache.http.HttpStatus.*;


/**
 * @author Vitalii Smokov 26.02.2019
 */
public class StatusCode {

  public final static int OK_200 = SC_OK;
  public final static int OK_202 = SC_ACCEPTED;
  public final static int BAD_REQUEST_400 = SC_BAD_REQUEST;
  public final static int UNAUTORIZED_401 = SC_UNAUTHORIZED;
  public final static int FORBIDDEN_403 = SC_FORBIDDEN;
  public final static int NOT_FOUND_404 = SC_NOT_FOUND;
  public final static int METHOD_NOT_ALLOWED_405 = SC_METHOD_NOT_ALLOWED;
  public final static int NOT_ACCEPTABLE_406 = SC_NOT_ACCEPTABLE;
  public final static int CONFLICT_409 = SC_CONFLICT;
  public final static int REQUEST_TOO_LONG_413 = SC_REQUEST_TOO_LONG;
  public final static int UNPROCESSABLE_ENTITY_422 = SC_UNPROCESSABLE_ENTITY;
  public final static int LOCKED_423 = SC_LOCKED;
  public final static int SERVER_ERROR_500 = SC_INTERNAL_SERVER_ERROR;
}
