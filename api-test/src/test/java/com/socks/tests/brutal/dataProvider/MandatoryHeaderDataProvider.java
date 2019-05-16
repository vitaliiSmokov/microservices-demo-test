package com.socks.tests.brutal.dataProvider;

import static com.socks.api.utils.RandomUtil.*;

import com.github.javafaker.*;
import com.socks.api.utils.*;
import java.util.stream.*;
import org.junit.jupiter.params.provider.*;

/**
 * @author Vitalii Smokov 01.03.2019
 */
public class MandatoryHeaderDataProvider {
  private static Faker faker = RandomUtil.getFaker();

  public static Stream<Arguments> requestHeadersNegative() {
    return Stream.of(
        Arguments.of("x-device-id", null),
        Arguments.of("x-device-id", ""),
        Arguments.of("x-device-id", "null"),
        Arguments.of("x-device-id", faker.lorem().characters(33)),
        Arguments.of("x-device-id", getRandomHexString(31)),
        Arguments.of("x-device-id", getRandomHexString(37)),
        Arguments.of("x-device-id", getRandomHexString(33).toUpperCase()),
        Arguments.of("x-device-id", "000000000000000000000000000000000"),
        Arguments.of("x-device-id", "<><><><><><><>>><><><><><><><><><"),
        Arguments.of("x-forwarded-for", "127"),
        Arguments.of("x-forwarded-for", "194.44.223.198.1"),
        Arguments.of("x-forwarded-for", "194.44.223"),
        Arguments.of("accept", null),
        Arguments.of("accept", ""),
        Arguments.of("accept", "111111"),
        Arguments.of("accept", "@#$%^^&&**"),
        Arguments.of("x-user-timezone", null),
        Arguments.of("x-user-timezone", ""),
        Arguments.of("x-user-timezone", "null"),
        Arguments.of("x-user-timezone", "111111"),
        Arguments.of("x-user-timezone", "@#$%^^&&**"),
        Arguments.of("user-agent", ""),
        Arguments.of("user-agent", "@#$%^^&&**"),
        Arguments.of("Content-Type", null),
        Arguments.of("Content-Type", "text/plain"),
        Arguments.of("Content-Type", "text/html"),
        Arguments.of("Content-Type", "text/json"),
        Arguments.of("Content-Type", "application/javascript"),
        Arguments.of("Content-Type", "text/javascript"),
        Arguments.of("Content-Type", "application/xml"),
        Arguments.of("Content-Type", "application/x-www-form-urlencoded"),
        Arguments.of("x-request-id", null),
        Arguments.of("x-request-id", ""),
        Arguments.of("x-request-id", "null"),
        Arguments.of("x-request-id", "00000000"),
        Arguments.of("x-request-id", "        "),
        Arguments.of("x-request-id", faker.lorem().characters(7)),
        Arguments.of("x-request-id", faker.lorem().characters(9))
    );
  }
}
