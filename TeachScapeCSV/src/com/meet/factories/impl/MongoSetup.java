package com.meet.factories.impl;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoSetup {

  private static final String MONGO_DB_NAME = "MeetUpDB";

  private static MongoSetup mongoSetup = null;

  private MongoClient mongoClient = null;

  private DB database = null;

  private MongoSetup() {
    setupMongoDB();
  }

  public static MongoSetup getInstance() {
    if (mongoSetup == null) {
      mongoSetup = new MongoSetup();
    } 
    return mongoSetup;
  }

  private void setupMongoDB() {
    try {
      mongoClient = new MongoClient("localhost");
      database = mongoClient.getDB(MONGO_DB_NAME);
    } catch (UnknownHostException e) {
      System.err.println("We could NOT connect to the MongoDB.");
      e.printStackTrace();
    }
  }

  public DB getDatabase() {
    return database;
  }
}
