package io.swagger.petstore.api.utils.db.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import io.qameta.allure.Step;
import io.swagger.petstore.api.utils.db.mongo.enums.MongoCollections;
import io.swagger.petstore.api.utils.db.mongo.enums.MongoDB;
import io.swagger.petstore.api.utils.db.mongo.enums.MongoFields;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static io.swagger.petstore.api.utils.db.mongo.MongoBase.mongoClient;

@Slf4j
public class MongoMethods {

  @Step("Find in mongo {2} = {3}")
  protected <T> T getMongoObjectEquals(
          MongoDB dbName, MongoCollections collection, Bson filter, Class<T> responseClass) {
    log.info("Get: {} from collection: {}", filter, collection.name());
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(
          mongoClient
              .getDatabase(dbName.getName())
              .getCollection(collection.name())
              .find(filter)
              .first()
              .toJson(),
          responseClass);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Step("Find in mongo {2} = {3}")
  protected <T> T getMongoObjectEquals(
          MongoDB dbName, MongoCollections collection, Bson filter, Bson sort, Class<T> responseClass) {
    log.info("Get: {} from collection: {}", filter, collection.name());
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(
          Objects.requireNonNull(
                  mongoClient
                      .getDatabase(dbName.getName())
                      .getCollection(collection.name())
                      .find(filter)
                      .sort(sort)
                      .first())
              .toJson(),
          responseClass);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Step("Find in mongo _id = {2}")
  public <T> T getMongoObjectByIDEquals(
      MongoDB dbName, MongoCollections collection, String value, Class<T> responseClass) {
    return getMongoObjectEquals(dbName, collection, eq("_id", new ObjectId(value)), responseClass);
  }

  protected <T> List<T> toList(FindIterable<Document> documents, Class<T> responseClass) {
    List<T> res = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    documents.forEach(
        (Block<Document>)
            document -> {
              //                            System.out.println(document.toJson());
              try {
                res.add(mapper.readValue(document.toJson(), responseClass));
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
    return res;
  }

  protected <T> List<T> toList(
          MongoDB dbName, MongoCollections collection, Bson filter, Class<T> responseClass) {
    System.out.println(filter);
    FindIterable<Document> documents =
        mongoClient.getDatabase(dbName.getName()).getCollection(collection.name()).find(filter);
    return toList(documents, responseClass);
  }

  protected void updateSmthDate(
      MongoDB dbName,
      MongoCollections collection,
      Bson filter,
      MongoFields dateField,
      DateTime expectedDate) {
    log.info("Update field: {} where: {}, set: {}", dateField.getField(), filter, expectedDate);
    Date date = expectedDate.toDate();
    Bson query = set(dateField.getField(), date);
    mongoClient
        .getDatabase(dbName.getName())
        .getCollection(collection.name())
        .updateOne(filter, query);
  }

  protected void validateUUID(String uuid) {
    if (uuid.length() < 24) {
      throw new AssertionError("Id " + uuid + " is not correct");
    }
  }
}
