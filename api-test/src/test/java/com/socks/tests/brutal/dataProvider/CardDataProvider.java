package com.socks.tests.brutal.dataProvider;

import static com.github.javafaker.CreditCardType.*;
import static com.socks.api.utils.RandomUtil.*;

import java.time.*;
import java.util.stream.*;
import org.junit.jupiter.params.provider.*;

/**
 * @author Vitalii Smokov 26.03.2019
 */
public class CardDataProvider {

  private static Stream<Arguments> getCardVendors() {
    return Stream.of(
        Arguments.of(MASTERCARD),
        Arguments.of(AMERICAN_EXPRESS),
//        Arguments.of(DISCOVER),
//        Arguments.of(DANKORT),
//        Arguments.of(DINERS_CLUB),
//        Arguments.of(FORBRUGSFORENINGEN),
//        Arguments.of(LASER),
//        Arguments.of(SOLO),
//        Arguments.of(SWITCH),
//        Arguments.of(JCB),
        Arguments.of(VISA)
    );
  }

  private static Stream<Arguments> getNegativeCardName() {
    return Stream.of(
        Arguments.of(""),
        Arguments.of("            "),
        Arguments.of("null"),
        Arguments.of(getFaker().lorem().characters(17)),
        Arguments.of(getFaker().lorem().characters(3))
    );
  }

  private static Stream<Arguments> getNegativeCardNumbers() {
    return Stream.of(
        Arguments.of(getFaker().number().digits(12)),
        Arguments.of(getFaker().number().digits(17)),
        Arguments.of("null"),
        Arguments.of(""),
        Arguments.of("         "),
        Arguments.of(getFaker().lorem().characters(15))
    );
  }

  private static Stream<Arguments> getNegativeCardHolderName() {
    return Stream.of(
        Arguments.of("1223445"),
        Arguments.of("1223445 qwerty"),
        Arguments.of(getFaker().date().birthday().toString()),
        Arguments.of(getFaker().artist().name().concat(getFaker().number().digit())),
        Arguments.of(getFaker().artist().name().concat("!")),
        Arguments.of(getFaker().funnyName().name().concat(getFaker().number().digit()))
    );
  }

  private static Stream<Arguments> getNegativeCardExpireMonth() {
    return Stream.of(
        Arguments.of("13"),
        Arguments.of("0"),
        Arguments.of("5"),
        Arguments.of("00"),
        Arguments.of("012"),
        Arguments.of(getFaker().number().digits(3)),
        Arguments.of("-2"),
        Arguments.of("1O"),
        Arguments.of("!@"),
        Arguments.of(getFairy().creditCard().getExpiryDate().getMonth().toString()),
        Arguments.of("null"),
        Arguments.of(""),
        Arguments.of("  "),
        Arguments.of("1f")
    );
  }

  private static Stream<Arguments> getNegativeCardCvv() {
    return Stream.of(
        Arguments.of("0"),
        Arguments.of("00"),
        Arguments.of(getFaker().number().digits(4)),
        Arguments.of(getFaker().number().digits(2)),
        Arguments.of("-23"),
        Arguments.of("!@%"),
        Arguments.of("null"),
        Arguments.of(""),
        Arguments.of("  "),
        Arguments.of("12a")
    );
  }


  private static Stream<Arguments> getNegativeCardExpireYear() {
    return Stream.of(
        Arguments.of(String.valueOf(LocalDate.now().minusYears(1).getYear())),
        Arguments.of(String.valueOf(LocalDate.now().minusYears(3000).getYear())),
        Arguments.of(String.valueOf(LocalDate.now().plusYears(100).getYear()))
    );
  }


}
