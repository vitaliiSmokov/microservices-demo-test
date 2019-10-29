package io.swagger.petstore.api.utils.db.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.swagger.petstore.api.utils.PropertyUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MongoBase {
    public static MongoClient mongoClient;
    private String dbURL;
    private String authLogin;
    private String authPassword;

    public MongoBase() {
        this.dbURL = "dbMongoURL()";
        this.authLogin = "config.mongoUser()";
        this.authPassword = "config.mongoPassword().replace(\"@\", \"%40\")";
    }

    public static void closeMongoConnection() {
        if (mongoClient != null) {
            log.info("Close mongo connection");
            mongoClient.close();
            mongoClient = null;
        }
    }

    public void initConnection() {
        if (mongoClient == null) {
            log.info("Init mongo connection");
            switch (PropertyUtil.getConfigs().env()) {
                case "DEV":
                    mongoClient =
                            MongoClients.create(
                                    String.format(
                                            "mongodb://"
                                                    + "%1$s:%2$s"
                                                    + "@%3$s/?"
                                                    + "authSource=admin&authMechanism=SCRAM-SHA-1",
                                            authLogin, authPassword, dbURL));
                    break;
                case "STAGE":
                    mongoClient =
                            MongoClients.create(
                                    String.format(
                                            "mongodb://"
                                                    + "%1$s:%2$s"
                                                    + "@%3$s"
                                                    + "/?authSource=admin&authMechanism=SCRAM-SHA-1&replicaSet=rs0",
                                            authLogin, authPassword, dbURL));
            }
        }
    }
}
