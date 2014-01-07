package com.meet.dao.impl;

import java.util.TreeSet;

import com.meet.dao.MeetUpGroup;
import com.meet.dao.QueryUserMeetUp;
import com.meet.dao.UserMeetUpGroup;
import com.meet.factories.impl.MongoSetup;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class QueryUserMeetUpMongo implements QueryUserMeetUp {

  public QueryUserMeetUpMongo() {
  }

  @Override
  public String[] getAllCompaniesOfMeetUpGroup(String aGroupName) {
    DB database = MongoSetup.getInstance().getDatabase();

    // MeetupGroup id
    DBCollection meetUpGroupCollection = database
        .getCollection(MeetUpGroup.class.getName());
    meetUpGroupCollection.setObjectClass(MeetUpGroupMongo.class);
    DBObject queryMeetUpGroup = new BasicDBObject("groupname", aGroupName);
    MeetUpGroupMongo meetUpGroup = (MeetUpGroupMongo) meetUpGroupCollection
        .findOne(queryMeetUpGroup);

    DBCollection userMeetUpGroupCollection = database
        .getCollection(UserMeetUpGroup.class.getName());
    meetUpGroupCollection.setObjectClass(UserMeetUpGroupMongo.class);

    DBObject queryUserMeetUpGroup = new BasicDBObject("meetupgroup_id._id",
        meetUpGroup.getId());
    
    DBObject fields = new BasicDBObject("user_id.company", 1);
    DBCursor resultUserMeetUpGroupCuror = userMeetUpGroupCollection.find(
        queryUserMeetUpGroup, fields);
    TreeSet<String> resultTreeSet = new TreeSet<>();

    for (DBObject item : resultUserMeetUpGroupCuror) {
      DBObject user = (DBObject) item.get("user_id");
      resultTreeSet.add((String) user.get("company"));
    }

    //Set<String> resultSet = resultTreeSet.descendingSet();
    
    return resultTreeSet.toArray(new String[resultTreeSet.size()]);
  }

}
