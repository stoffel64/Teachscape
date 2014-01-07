package com.meet.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.types.ObjectId;

import com.meet.dao.MeetUpGroup;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MeetUpGroupMongo implements MeetUpGroup, DBObject, Serializable {

  private static final long serialVersionUID = -7772097806425220L;

  private ObjectId _id;

  private String groupName;

  private String mailingListEmail;

  public MeetUpGroupMongo(String aGroupName, String aMailingListEmail) {
    groupName = aGroupName;
    mailingListEmail = aMailingListEmail;
    this.generateId();
  }

  public MeetUpGroupMongo() {
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

    document.put("_id", this._id);
    document.put("groupname", groupName);
    document.put("mailinglistemail", mailingListEmail);

    return document;
  }

  public void makePojoFromBson(DBObject aBson) {
    BasicDBObject basicDBObject = (BasicDBObject) aBson;

    _id = (ObjectId) basicDBObject.get("_id");
    groupName = basicDBObject.getString("groupname");
    mailingListEmail = basicDBObject.getString("mailinglistemail");
  }

  @Override
  public String getGroupName() {
    return groupName;
  }

  @Override
  public String getMailingListEmail() {
    return mailingListEmail;
  }

  @Override
  public void setGroupName(final String aGroupName) {
    groupName = aGroupName;
  }

  @Override
  public boolean containsField(String aField) {
    return (aField.equals("_id") || aField.equals("groupname") || aField
        .equals("mailinglistemail"));
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
      case "groupname" :
        return groupName;
      case "mailinglistemail" :
        return mailingListEmail;
      default :
        return null;
    }
  }

  @Override
  public Set<String> keySet() {
    Set<String> set = new HashSet<String>();

    set.add("_id");
    set.add("groupname");
    set.add("mailinglistemail");

    return set;
  }

  @Override
  public Object put(String aField, Object aObject) {
    switch (aField) {
      case "_id" :
        _id = (ObjectId) aObject;
        break;
      case "groupname" :
        groupName = (String) aObject;
        break;
      case "mailinglistemail" :
        mailingListEmail = (String) aObject;
        break;
      default :
        return null;
    }
    return aObject;
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

    if (_id != null) {
      map.put("_id", _id);
    }

    if (groupName != null) {
      map.put("groupname", groupName);
    }

    if (mailingListEmail != null) {
      map.put("mailinglistemail", mailingListEmail);
    }

    return map;
  }

  @Override
  public boolean isPartialObject() {
    return false;
  }

  @Override
  public void markAsPartialObject() {
    throw new RuntimeException("Unsupported method.");
  }
}
