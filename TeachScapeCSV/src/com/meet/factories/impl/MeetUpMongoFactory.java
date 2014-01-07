package com.meet.factories.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.meet.dao.MeetUpGroup;
import com.meet.dao.QueryUserMeetUp;
import com.meet.dao.User;
import com.meet.dao.UserMeetUpGroup;
import com.meet.dao.impl.MeetUpGroupMongo;
import com.meet.dao.impl.QueryUserMeetUpMongo;
import com.meet.dao.impl.UserMeetUpGroupMongo;
import com.meet.dao.impl.UserMongo;
import com.meet.factories.MeetUpFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MeetUpMongoFactory implements MeetUpFactory {

  public MeetUpMongoFactory() {
  }

  @Override
  public void setupUser(String aName, String aUserID, String aTitle,
      String aCompany) {
    DB database = MongoSetup.getInstance().getDatabase();
    DBCollection collection = database.getCollection(User.class.getName());
    collection.setObjectClass(UserMongo.class);

    UserMongo userMongo = (UserMongo) collection.findOne(new BasicDBObject(
        "userid", aUserID));

    if (userMongo == null || !userMongo.getUserId().equals(aUserID)) {
      userMongo = new UserMongo(aUserID, aName, aTitle, aCompany);
      collection.insert(userMongo);
    }
  }

  @Override
  public void setupUserMeetUpGroup(String aUserId, String aGroupName,
      String aMemberID, String aJoinedGroupOn, String aLastVisitedGroupOn,
      String aLastAttended, String aTotalRSVPs, String aRSVPedYes,
      String aRSVPedMaybe, String aRSVPedNo, String aMeetupsAttended,
      String aNoShows, String aIntro, String aPhoto,
      String aAssistantOrganizer, String aMailingList,
      String aURLOfMemberProfile) {
    DB database = MongoSetup.getInstance().getDatabase();
    DBCollection collection = database.getCollection(UserMeetUpGroup.class
        .getName());
    collection.setObjectClass(UserMeetUpGroupMongo.class);

    UserMeetUpGroupMongo userMeetUpGroup = (UserMeetUpGroupMongo) collection
        .findOne(new BasicDBObject("memberid", aMemberID));

    if (userMeetUpGroup == null
        || userMeetUpGroup.getMemberId().equals(aMemberID)) {
      DBCollection userCollection = database
          .getCollection(User.class.getName());
      userCollection.setObjectClass(UserMongo.class);
      UserMongo userMongo = (UserMongo) userCollection
          .findOne(new BasicDBObject("userid", aUserId));

      DBCollection meetUpGroupCollection = database
          .getCollection(MeetUpGroup.class.getName());
      meetUpGroupCollection.setObjectClass(MeetUpGroupMongo.class);
      MeetUpGroupMongo existingMeetUpGroup = (MeetUpGroupMongo) meetUpGroupCollection
          .findOne(new BasicDBObject("groupname", aGroupName));

      Date joinGroupOn = parseDate(aJoinedGroupOn);
      Date lastVisitedGroupOn = parseDate(aLastVisitedGroupOn);
      Date lastAttended = parseDate(aLastAttended);

      boolean intro = "yes".equalsIgnoreCase(aIntro);
      boolean photo = "yes".equalsIgnoreCase(aPhoto);
      boolean assistantOrganizer = "yes".equalsIgnoreCase(aAssistantOrganizer);

      try {
        userMeetUpGroup = new UserMeetUpGroupMongo(userMongo,
            existingMeetUpGroup, aMemberID, joinGroupOn, lastVisitedGroupOn,
            lastAttended, Integer.parseInt(aTotalRSVPs),
            Integer.parseInt(aRSVPedYes), Integer.parseInt(aRSVPedMaybe),
            Integer.parseInt(aRSVPedNo), Integer.parseInt(aMeetupsAttended),
            Integer.parseInt(aNoShows), intro, photo, assistantOrganizer,
            aMailingList, new URL(aURLOfMemberProfile));
        collection.insert(userMeetUpGroup);
      } catch (NumberFormatException e) {
        e.printStackTrace();
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }
  }

  private Date parseDate(String aDateString) {
    if (aDateString == null || aDateString.isEmpty()) {
      return null;
    }
    DateFormat dateFormat1 = new SimpleDateFormat("dd/mm/yyyy");
    DateFormat dateFormat2 = new SimpleDateFormat("dd/mm/yy");

    try {
      return dateFormat1.parse(aDateString);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    try {
      return dateFormat2.parse(aDateString);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void setupMeetUpGroup(String aGroupName, String aMailingListEmail) {
    DB database = MongoSetup.getInstance().getDatabase();
    DBCollection collection = database.getCollection(MeetUpGroup.class
        .getName());
    collection.setObjectClass(MeetUpGroupMongo.class);

    MeetUpGroupMongo existingMeetUpGroup = (MeetUpGroupMongo) collection
        .findOne(new BasicDBObject("groupname", aGroupName));

    if (existingMeetUpGroup == null
        || !existingMeetUpGroup.getGroupName().equals(aGroupName)) {
      MeetUpGroupMongo meetUpGroup = new MeetUpGroupMongo(aGroupName,
          aMailingListEmail);
      collection.insert(meetUpGroup);
    }
  }

  @Override
  public QueryUserMeetUp setupQueryUserMeetUpGroup() {
    return new QueryUserMeetUpMongo();
  }

}
