package com.meet.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.types.ObjectId;

import com.meet.dao.MeetUpGroup;
import com.meet.dao.User;
import com.meet.dao.UserMeetUpGroup;
import com.meet.factories.impl.MongoSetup;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserMongo implements User, DBObject, Serializable {

  private static final long serialVersionUID = 6880897256918526691L;

  private ObjectId _id;

  private String userId;

  private String userName;

  private String title;

  private String company;

  public UserMongo() {
  }

  public UserMongo(String aUserId, String aUserName, String aTitle,
      String aCompany) {
    userId = aUserId;
    userName = aUserName;
    title = aTitle;
    company = aCompany;
    this.generateId();
  }

  public ObjectId getId() {
    return this._id;
  }

  public void setId(ObjectId _id) {
    this._id = _id;
  }

  public void generateId() {
    if (this._id == null)
      this._id = new ObjectId();
  }

  public DBObject bsonFromPojo() {
    BasicDBObject document = new BasicDBObject();

    document.put("_id", _id);
    document.put("userid", userId);
    document.put("username", userName);
    document.put("title", title);
    document.put("company", company);

    return document;
  }

  public void makePojoFromBson(DBObject aBson) {
    BasicDBObject basicDBObject = (BasicDBObject) aBson;

    _id = (ObjectId) basicDBObject.get("_id");
    userId = basicDBObject.getString("userid");
    userName = basicDBObject.getString("username");
    title = basicDBObject.getString("userid");
    company = basicDBObject.getString("company");
  }

  @Override
  public String getUserId() {
    return userId;
  }

  @Override
  public void setUserId(final String aUserId) {
    userId = aUserId;
  }

  @Override
  public String getUserName() {
    return userName;
  }

  @Override
  public void setUserName(final String aUserName) {
    userName = aUserName;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setTitle(final String aTilte) {
    title = aTilte;
  }

  @Override
  public String getCompany() {
    return company;
  }

  @Override
  public void setCompany(final String aCompany) {
    company = aCompany;
  }

  @Override
  public UserMeetUpGroup findUserMeetUpGroup(final String aGroupName) {
    
    // get the meetup group id 
    DB database = MongoSetup.getInstance().getDatabase();
    DBCollection meetUpGroupCollection = database
        .getCollection(MeetUpGroup.class.getName());
    meetUpGroupCollection.setObjectClass(MeetUpGroupMongo.class);
    MeetUpGroupMongo existingMeetUpGroup = (MeetUpGroupMongo) meetUpGroupCollection
        .findOne(new BasicDBObject("groupname", aGroupName));
    
    // query the user meet up groups using the obtained meet up group
    database = MongoSetup.getInstance().getDatabase();
    DBCollection collection = database.getCollection(UserMeetUpGroup.class
        .getName());
    collection.setObjectClass(UserMeetUpGroupMongo.class);

    BasicDBObject userGroupQuery = new BasicDBObject();
    userGroupQuery.put("user_id", _id);
    userGroupQuery.put("meetupgroup_id", existingMeetUpGroup.getGroupName());
    
    UserMeetUpGroupMongo userMeetUpGroupMongo = 
        (UserMeetUpGroupMongo) collection.findOne(userGroupQuery);
    
    return userMeetUpGroupMongo;
  }

  @Override
  public List<UserMeetUpGroup> getUserMeetUpGroups() {
    DB database = MongoSetup.getInstance().getDatabase();
    DBCollection collection = database.getCollection(UserMeetUpGroup.class
        .getName());
    collection.setObjectClass(UserMeetUpGroupMongo.class);

    DBCursor dbCursor = collection
        .find(new BasicDBObject("user_id", _id));
    
    List<UserMeetUpGroup> userMeetUpGroups = new ArrayList<>();
    
    for (DBObject item : dbCursor) {
      userMeetUpGroups.add((UserMeetUpGroup) item);
    }
    
    return userMeetUpGroups;
  }

  @Override
  public boolean containsField(String aField) {
    return (aField.equals("_id") || aField.equals("userid")
        || aField.equals("username") || aField.equals("title")
        || aField.equals("company") || aField.equals("meetupgroups"));
  }

  @Override
  public boolean containsKey(String aKey) {
    return containsField(aKey);
  }

  @Override
  public Object get(String aField) {
    switch (aField) {
      case "_id" :
        return _id;
      case "userid" :
        return userId;
      case "username" :
        return userName;
      case "title" :
        return title;
      case "company" :
        return company;
      default :
        return null;
    }
  }

  @Override
  public Set<String> keySet() {
    Set<String> set = new HashSet<>();

    set.add("_id");
    set.add("userid");
    set.add("username");
    set.add("title");
    set.add("company");
    set.add("meetupgroups");

    return set;
  }

  @Override
  public Object put(String aField, Object aValue) {
    switch (aField) {
      case "_id" :
        _id = (ObjectId) aValue;
        return aValue;
      case "userid" :
        userId = (String) aValue;
        return aValue;
      case "username" :
        userName = (String) aValue;
        return aValue;
      case "title" :
        title = (String) aValue;
        return aValue;
      case "company" :
        company = (String) aValue;
        return aValue;
      default :
        return null;
    }
  }

  @Override
  public void putAll(BSONObject aBson) {
    for (String key : aBson.keySet()) {
      put(key, aBson.get(key));
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void putAll(@SuppressWarnings("rawtypes") Map aMap) {
    for (Map.Entry<String, Object> entry : (Set<Map.Entry<String, Object>>) aMap
        .entrySet()) {
      put(entry.getKey().toString(), entry.getValue());
    }
  }

  @Override
  public Object removeField(String arg0) {
    throw new RuntimeException("Unsupported method.");
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Map toMap() {
    Map<String, Object> map = new HashMap<String, Object>();

    if (this._id != null)
      map.put("_id", _id);

    if (userId != null)
      map.put("userid", userId);

    if (userName != null)
      map.put("username", userName);

    if (title != null)
      map.put("title", title);

    if (company != null)
      map.put("company", company);

    return map;
  }

  @Override
  public boolean isPartialObject() {
    return false;
  }

  @Override
  public void markAsPartialObject() {
    throw new RuntimeException("Method not implemented.");
  }

}
