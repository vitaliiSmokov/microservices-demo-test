package io.swagger.petstore.api.utils.db.mongo;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import io.swagger.petstore.api.utils.db.mongo.enums.MongoCollections;
import io.swagger.petstore.api.utils.db.mongo.enums.MongoDB;
import org.bson.Document;

public class MongoHelper extends MongoBase {

    private static MongoClient getMongoClient() {
        if (mongoClient == null) {
            new MongoBase().initConnection();
        }
        return mongoClient;
    }

    private static MongoCollection<Document> getCollectionFromDB(
            MongoDB db, MongoCollections collection) {
        return getMongoClient().getDatabase(db.getName()).getCollection(collection.name());
    }
}
