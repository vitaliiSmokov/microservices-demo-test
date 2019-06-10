package com.socks.ui.constants;

import lombok.*;

/**
 * @author Vitalii Smokov 10.04.2019
 */

public abstract class SecurityQuestionCategories {

  @Getter
  public enum INPUT {
    BIRTH("What is your bithday?"),
    BIRTH_CITY("What is you bith city?"),
    FIRST_JOB("In what city or town was your first job?"),
    SIBLING_LIVE("In what city does your nearest sibling live?"),
    SPOUSE_MEET("In what city did you meet your spouse/signifi"),
    NICKNAME("What was your childhood nickname?"),
    STREET("What street did you live on in third grade?"),
    CHILDHOOD_FRIEND("What is the name of your favorite childhood f"),
    COLLEGE_NAME("What is the name of a college you applied to ");

    private String question;

    INPUT(String question) {
      this.question = question;
    }

    public static String getCategoryName() {
      return "INPUT";
    }
  }

  @Getter
  public enum DATE {
    SPOUSE_BIRTH("When is your spouse`s or girlfriend/boyfriend"),
    SIBLING_BIRTH("When is your oldest sibling`s birthday?"),
    PARENT_ANNIVERSARY("When is your parents anniversary?"),
    FRIEND_BIRTH("When is your best friends birthday?"),
    ANNIVERSARY("When is your anniversary?");

    private String question;

    DATE(String question) {
      this.question = question;
    }

    public static String getCategoryName() {
      return "DATE";
    }
  }

  @Getter
  public enum DIGITS {
    DRIVING_LICENCE("What are the last four digits of your driver"),
    PHONE("What are the last four digits of your phone n"),
    CAR("What year did you get your car?"),
    JOB("What year did you get your first job?"),
    HIGH_SCHOOL("What year did you graduate from high school?");

    private String question;

    DIGITS(String question) {
      this.question = question;
    }

    public static String getCategoryName() {
      return "DIGITS";
    }
  }
}

