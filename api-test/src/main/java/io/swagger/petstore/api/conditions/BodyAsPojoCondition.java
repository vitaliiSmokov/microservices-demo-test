package io.swagger.petstore.api.conditions;

import io.restassured.response.Response;
import lombok.ToString;

import static org.assertj.core.api.Assertions.assertThat;

@ToString
public class BodyAsPojoCondition<T> implements Condition {

    private final T pojo;
    private String jsonPath;

    public BodyAsPojoCondition(T pojo) {
        this("", pojo);
    }

    public BodyAsPojoCondition(String jsonPath, T pojo) {
        this.pojo = pojo;
        this.jsonPath = jsonPath;
    }

    @Override
    public void verify(Response response) {
        assertThat(response.body().jsonPath().getObject(jsonPath, pojo.getClass())).isEqualTo(pojo);
    }

    @Override
    public void verifyNot(Response response) {
        assertThat(response.body().jsonPath().getObject(jsonPath, pojo.getClass())).isNotEqualTo(pojo);
    }
}
