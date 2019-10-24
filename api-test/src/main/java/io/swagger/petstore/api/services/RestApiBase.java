package io.swagger.petstore.api.services;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class RestApiBase {

  protected Response postResource(RequestSpecification spec, String requestURL, Object body) {
    return given(spec.with().body(body)).post(requestURL);
  }

  protected Response postResource(
          RequestSpecification spec, String requestURL, Map<String, ?> parametersMap) {
    return given(spec.with().formParams(parametersMap)).post(requestURL);
  }

  protected Response postResource(
      RequestSpecification spec,
      String requestURL,
      String parameterName,
      Object... parameterValues) {
    return given(spec.with().formParam(parameterName, parameterValues)).post(requestURL);
  }

  protected <T> T getResource(
          RequestSpecification spec, String requestURL, String fieldToExtract, Class<T> responseClass) {
    return getResource(spec, requestURL, HttpStatus.SC_OK)
        .then()
        .extract()
        .body()
        .jsonPath()
        .getObject(fieldToExtract, responseClass);
  }

  protected Response getResource(RequestSpecification spec, String requestURL) {
    return given(spec).get(requestURL);
  }

  protected Response getResource(RequestSpecification spec, String path, Object... pathParams) {
    return given(spec).get(path, pathParams);
  }

  protected ResponseSpecification getResponseSpec() {
    return new ResponseSpecBuilder()
        .expectContentType(ContentType.JSON)
        .expectResponseTime(Matchers.lessThan(15000L))
        .build();
  }
}
