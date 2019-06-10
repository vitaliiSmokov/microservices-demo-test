package com.socks.ui.constants;

/**
 * @author Vitalii Smokov 26.02.2019
 */
public final class Endpoints {

  public static final class Registration {

    private static final String REGISTRATION_SERVICE_URL = "/auth/v2/registration/";

    public static final String COUNTRIES = REGISTRATION_SERVICE_URL + "countries";
    public static final String LANGUAGES = REGISTRATION_SERVICE_URL + "languages";
    public static final String TIMEZONES = REGISTRATION_SERVICE_URL + "timezones";
    public static final String REGIONS = REGISTRATION_SERVICE_URL + "regions?country=%s";
    public static final String SIGN_UP = REGISTRATION_SERVICE_URL + "sign-up";
    public static final String REGISTRATION_CHECK_OTP = REGISTRATION_SERVICE_URL + "otp/check";
    public static final String REGISTRATION_RESENT_OTP = REGISTRATION_SERVICE_URL + "otp/resend";
    public static final String FINISH_REGISTRATION = REGISTRATION_SERVICE_URL + "finish";
    public static final String SECURITY_QUESTIONS = REGISTRATION_SERVICE_URL + "questions";
  }

}
