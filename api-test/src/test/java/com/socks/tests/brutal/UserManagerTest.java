package com.socks.tests.brutal;

import static com.socks.api.conditions.Conditions.body;
import static com.socks.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import com.github.javafaker.Faker;
import com.socks.api.model.User;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UserManagerTest {

  private UserApiService userApiService = new UserApiService();
  private Faker faker = new Faker(); //new Faker(new Locale("ru"));
  private Person person = Fairy.create().person(); // create(new Locale("ru"));

  @BeforeAll
  static void setUp() {
    RestAssured.baseURI = "http://68.183.67.80/";
  }

  @Test
  void testCanRegisterUserWithValidCredentials() {

//        given
    User user = new User()
        .setFirstName(faker.name().firstName())
        .setLastName(faker.name().lastName())
//                .setUsername(RandomStringUtils.randomAlphanumeric(6))
        .setUsername(faker.name().username())
        .setEmail(faker.internet().emailAddress())
        .setPassword("test123");

//        when
    RestAssured
        .given()
        .contentType(ContentType.JSON)
        .filters(
            new RequestLoggingFilter(),
            new ResponseLoggingFilter()
        ).body(user)
        .when()
        .post("register")
//                Then
        .then()
        .assertThat()
        .statusCode(200)
        .body("id", not(isEmptyString()));
  }

  @Test
  void testCanRegisterUserWithInValidCredentials() {

    User user = new User()
        .setFirstName(person.getFirstName())
        .setLastName(person.getLastName())
        .setUsername(person.getUsername())
        .setEmail(person.getEmail())
        .setPassword("test123");

    userApiService
        .registerUser(user)
        .shouldHave(statusCode(200))
        .shouldHave(body("id", not(isEmptyString())));
  }
}
