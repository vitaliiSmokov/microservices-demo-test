package com.socks.tests.brutal.dataProvider;

/**
 * @author Vitalii Smokov 07.03.2019
 */
public class DataProviders {

  public static final String INCOMPATIBLE_HEADERS =
      "com.lukspay.api.dataProvider.MandatoryHeaderDataProvider#requestHeadersNegative";

  public static class CredentialsDataProviders {

    private static final String PATH_TO_CLASS = "com.lukspay.api.dataProvider.CredentialsDataProvider";
    public static final String INCOMPATIBLE_PASSWORDS = PATH_TO_CLASS + "#getIncompatiblePassword";
    public static final String SQL_INJECTION = PATH_TO_CLASS + "#getSqlInjection";
    public static final String INCOMPATIBLE_EMAIL = PATH_TO_CLASS + "#incompatibleEmail";
    public static final String INCOMPATIBLE_OTP = PATH_TO_CLASS + "#incompatibleOtp";
    public static final String INCOMPATIBLE_NAME = PATH_TO_CLASS + "#incompatibleName";
    public static final String INCOMPATIBLE_ID_NUMBER = PATH_TO_CLASS + "#incompatibleID";
    public static final String INCOMPATIBLE_COUNTRY_CODE = PATH_TO_CLASS + "#incompatibleCountryCode";
    public static final String INCOMPATIBLE_ADDRESS = PATH_TO_CLASS + "#incompatibleAddress";
    public static final String INCOMPATIBLE_ZIP = PATH_TO_CLASS + "#incompatibleZip";
  }

  public static class CardDataProviders {

    private static final String PATH_TO_CLASS = "com.lukspay.api.dataProvider.CardDataProvider";
    public static final String CARD_VENDORS = PATH_TO_CLASS + "#getCardVendors";
    public static final String NEGATIVE_CARD_NUMBERS = PATH_TO_CLASS + "#getNegativeCardNumbers";
    public static final String NEGATIVE_CARD_NAME = PATH_TO_CLASS + "#getNegativeCardName";
    public static final String NEGATIVE_CARD_HOLDER_NAME = PATH_TO_CLASS + "#getNegativeCardHolderName";
    public static final String NEGATIVE_CARD_EXPIRE_MONTH = PATH_TO_CLASS + "#getNegativeCardExpireMonth";
    public static final String NEGATIVE_CARD_EXPIRE_YEAR = PATH_TO_CLASS + "#getNegativeCardExpireYear";
    public static final String NEGATIVE_CARD_CVV = PATH_TO_CLASS + "#getNegativeCardCvv";
  }

  public static class OwaspDataProviders {

    private static final String PATH_TO_CLASS = "com.lukspay.api.dataProvider.OwaspDataProvider";
    public static final String FORMAT_STRING_ERRORS = PATH_TO_CLASS + "#getFormatStringErrors";
    public static final String INT_OVERFLOWS = PATH_TO_CLASS + "#getIntOverflows";
    public static final String PASSIVE_SQL_INJECTION = PATH_TO_CLASS + "#getPassiveSQLInjection";
    public static final String ACTIVE_SQL_INJECTION = PATH_TO_CLASS + "#getActiveSQLInjection";
  }
}
