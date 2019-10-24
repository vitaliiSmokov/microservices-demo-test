package io.swagger.petstore.api.conditions;

import lombok.experimental.UtilityClass;
import org.hamcrest.Matcher;

@UtilityClass
public class Conditions {

    public StatusCodeCondition statusCode(int code) {
        return new StatusCodeCondition(code);
    }

    public BodyCondition body(String id, Matcher<?> matcher) {
        return new BodyCondition(id, matcher);
    }

    public <T> BodyAsPojoCondition bodyAsPojo(T pojo) {
        return new BodyAsPojoCondition<>(pojo);
    }

    public HeaderCondition header(String id, Matcher<?> matcher) {
        return new HeaderCondition(id, matcher);
    }
}
