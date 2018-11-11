package com.socks.api.model;

@ToString
@Getter
@Setter
public class User {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;
}
