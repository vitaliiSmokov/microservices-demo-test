package io.swagger.petstore.api.params;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserParams {

    private static HashMap<String, Object> queryParams;

    private UserParams(HashMap<String, Object> builder) {
        queryParams = builder;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, Object> getParams() {
        return queryParams;
    }

    @Override
    public String toString() {
        return Arrays.toString(queryParams.entrySet().toArray());
    }

    public final static class Builder {

        private final HashMap<String, Object> query = new HashMap<>();

        public Builder addUsername(String username) {
            query.put("username", username);
            return this;
        }

        public Builder addPassword(String password) {
            query.put("password", password);
            return this;
        }

        public synchronized UserParams build() {
            return new UserParams(this.query);
        }
    }
}
