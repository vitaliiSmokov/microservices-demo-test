package com.socks.tests.brutal.dataProvider;

import static com.socks.api.utils.RandomUtil.*;

import java.util.stream.*;
import org.junit.jupiter.params.provider.*;

/**
 * @author Vitalii Smokov 05.03.2019
 */
public class CredentialsDataProvider {

  private static Stream<Arguments> getIncompatiblePassword() {
    return Stream.of(
        Arguments.of("qwert"),
        Arguments.of("123"),
        Arguments.of("qweert123"),
        Arguments.of("цуук12"),
        Arguments.of("P*ss77"),
        Arguments.of("p#sswor"),
        Arguments.of("       "),
        Arguments.of("^u&Mt3&I52^u&Mt3&I52^u&Mt3&I521"),
        Arguments.of("_-#%*+143456"),
        Arguments.of("П%роль77"),
        Arguments.of("密碼*密碼77"),
        Arguments.of("パスワード@77"),
        Arguments.of("null")
    );
  }

  private static Stream<Arguments> getSqlInjection() {
    return Stream.of(
        Arguments.of("'"),
        Arguments.of("''"),
        Arguments.of("\''"),
        Arguments.of("--"),
        Arguments.of("#"),
        Arguments.of("/*"),
        Arguments.of("*/"),
        Arguments.of("\""),
        Arguments.of("’ or ‘1’=’1"),
        Arguments.of("\" or \"1\"=\"1"),
        Arguments.of("') or ('1'='1"),
        Arguments.of("/**/"),
        Arguments.of("+"),
        Arguments.of(" or 1=1"),
        Arguments.of("%"),
        Arguments.of("'#")
    );
  }

  private static Stream<Arguments> incompatibleEmail() {
    return Stream.of(
        Arguments.of("null"),
        Arguments.of(":"),
        Arguments.of("?"),
        Arguments.of("*"),
        Arguments.of("|"),
        Arguments.of("\""),
        Arguments.of("\\"),
        Arguments.of("/"),
        Arguments.of("¶"),
        Arguments.of("@"),
        Arguments.of("пошта"),
        Arguments.of("ლეილაკოლირი"),
        Arguments.of("ליילהקוליר"),
        Arguments.of("萊拉科利爾"),
        Arguments.of("레이 라 콜리에"),
        Arguments.of("レイラコリアー"),
        Arguments.of("ليلىكولير")
    );
  }

  private static Stream<Arguments> incompatibleOtp() {
    return Stream.of(
        Arguments.of("null"),
        Arguments.of("1"),
        Arguments.of("!@#$"),
        Arguments.of("12,3"),
        Arguments.of("-124"),
        Arguments.of("0.12"),
        Arguments.of("1234567890"),
        Arguments.of("\\’’"),
        Arguments.of("--#/**/"),
        Arguments.of("oooo"),
        Arguments.of("000"),
        Arguments.of(""),
        Arguments.of("    "),
        Arguments.of("qwerty"),
        Arguments.of("укенг")
    );
  }

  private static Stream<Arguments> incompatibleName() {
    return Stream.of(
        Arguments.of(""),
        Arguments.of("null"),
        Arguments.of(getFaker().lorem().characters(51)));
  }
  
  private static Stream<Arguments> incompatibleID() {
    return Stream.of(
        Arguments.of(""),
        Arguments.of("null"),
        Arguments.of(getFaker().lorem().characters(10)));
  }
  
  private static Stream<Arguments> incompatibleCountryCode() {
    return Stream.of(
        Arguments.of(""),
        Arguments.of(" "),
        Arguments.of("11"),
        Arguments.of("we"),
        Arguments.of("йу"),
        Arguments.of("?%"),
        Arguments.of("?UA"),
        Arguments.of("Ua"),
        Arguments.of("UAT"),
        Arguments.of("U"),
        Arguments.of("null"),
        Arguments.of("%0"));
  }

  private static Stream<Arguments> incompatibleAddress() {
    return Stream.of(
        Arguments.of(""),
        Arguments.of("null"),
        Arguments.of(getFaker().lorem().characters(101)));
  }

  private static Stream<Arguments> incompatibleZip() {
    return Stream.of(
        Arguments.of(""),
        Arguments.of("null"),
        Arguments.of(getFaker().lorem().characters(10)));
  }


}
