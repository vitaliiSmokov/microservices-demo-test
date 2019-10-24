package io.swagger.petstore.api.endpoints;

import lombok.Getter;

@Getter
public enum UserServiceEnpoints {
    USER("user"),
    USER_BY_USERNAME("user/{}");

    private String url;

    UserServiceEnpoints(String url) {
    this.url = url;
    }

    public String getUrl(String parameter) {
        return url.replace("{}", parameter);
    }
}
