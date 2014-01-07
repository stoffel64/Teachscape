package com.meet.factories;

import com.meet.factories.impl.MeetUpMongoFactory;

public class MeetUpFactoryBuilder {
  
  public static final String MONGO_FACTORY = "MONGO_DB";
  
  public static final String MSQL_FACTORY = "MSQL_DB";
  
  public static final String CASSANDRA_FACTORY = "CASSANDRA_DB";
  
  private static MeetUpFactory meetUpFactory = null;
  
  public static MeetUpFactory getInstance(String factoryType) {
    if (meetUpFactory != null) {
      return meetUpFactory;
    }
    else {
      switch(factoryType) {
        case MONGO_FACTORY :
          meetUpFactory = setupMongoDBFactory();
          break;
        case MSQL_FACTORY :
          meetUpFactory = setupMySQLDBFatory();
          break;
        case CASSANDRA_FACTORY :
          meetUpFactory = setupCassandraFactory();
          break;
          default :
            System.err.println("The factory type has to be specified.");
            System.exit(0);  // not nice but for here it is fine.
            break;
      }
      return meetUpFactory;
    }
  }
  
  private static MeetUpFactory setupCassandraFactory() {
    throw new RuntimeException("Method not implemented yet!");
  }
  
  private static MeetUpFactory setupMySQLDBFatory() {
    throw new RuntimeException("Method not implemented yet!");
  }
  
  private static MeetUpFactory setupMongoDBFactory() {
    return new MeetUpMongoFactory();
  }
}
