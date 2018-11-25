package com.socks.api;

import com.socks.api.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@RequiredArgsConstructor
//@Slf4j
public class AssertableResponse {

    private final ValidatableResponse response;

    public AssertableResponse(ValidatableResponse validatableResponse) {
        this.response = validatableResponse;
    }

    @Step("api response should have {condition}")
//    @Step("api response should have {0}")
    public AssertableResponse shouldHave(Condition condition) {
        Logger log = LoggerFactory.getLogger(AssertableResponse.class);
        log.info("verify :" + condition);
        condition.verify(response);
        return this;
    }
}
