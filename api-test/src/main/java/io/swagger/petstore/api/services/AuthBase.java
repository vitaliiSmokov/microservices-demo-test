package io.swagger.petstore.api.services;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.swagger.petstore.api.utils.PropertyUtil;

import java.util.Arrays;
import java.util.List;

public class AuthBase extends RestApiBase {

    public RequestSpecification initSpec(String xAuthToken, ContentType contentType) {
        return new RequestSpecBuilder()
                .setContentType(contentType)
                .setBaseUri(PropertyUtil.getConfigs().baseUrl())
                .addHeader("X-AUTH-TOKEN", xAuthToken)
                .addFilters(getFilters()).build();
    }

    public RequestSpecification initSpec(ContentType contentType) {
        return new RequestSpecBuilder()
                .setContentType(contentType)
                .setBaseUri(PropertyUtil.getConfigs().baseUrl())
                .addFilters(getFilters()).build();
    }

    private List<Filter> getFilters() {
        if (PropertyUtil.getConfigs().logs()) {
            return Arrays.asList(
                    new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());
        }
        return Arrays.asList(new ErrorLoggingFilter(), new AllureRestAssured());
    }
}
