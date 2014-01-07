package com.meet.dao.impl;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.types.ObjectId;

import com.meet.dao.MeetUpGroup;
import com.meet.dao.User;
import com.meet.dao.UserMeetUpGroup;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserMeetUpGroupMongo
    implements
      UserMeetUpGroup,
      DBObject,
      Serializable {

  private static final long serialVersionUID = 6587691824060549794L;

  private ObjectId _id;

  private User user;

  private MeetUpGroup meetUpGroup;

  private String memeberId;

  private Date joinedGroupOn;

  private Date lastVisitedGroupOn;

  private Date lastAttended;

  private int totalRSVPs;

  private int rsvpedYes;

  private int rsvpedMaybe;

  private int rsvpedNo;

  private int meetupsAttended;

  private int noShows;

  private boolean intro;

  private boolean photo;

  private boolean assistantOrganizer;

  private String mailingList;

  private String urlOfMemberProfile;

  public UserMeetUpGroupMongo() {
  }

  public UserMeetUpGroupMongo(UserMongo user, MeetUpGroupMongo meetUpGroup,
      String memeberId, Date joinedGroupOn, Date lastVisitedGroupOn,
      Date lastAttended, int totalRSVPs, int rsvpedYes, int rsvpedMaybe,
      int rsvpedNo, int meetupsAttended, int noShows, boolean intro,
      boolean photo, boolean assistantOrganizer, String mailingList,
      URL urlOfMemberProfile) {
    this.user = user;
    this.meetUpGroup = meetUpGroup;
    this.memeberId = memeberId;
    this.joinedGroupOn = joinedGroupOn;
    this.lastVisitedGroupOn = lastVisitedGroupOn;
    this.lastAttended = lastAttended;
    this.totalRSVPs = totalRSVPs;
    this.rsvpedYes = rsvpedYes;
    this.rsvpedMaybe = rsvpedMaybe;
    this.rsvpedNo = rsvpedNo;
    this.meetupsAttended = meetupsAttended;
    this.noShows = noShows;
    this.intro = intro;
    this.photo = photo;
    this.assistantOrganizer = assistantOrganizer;
    this.mailingList = mailingList;
    this.urlOfMemberProfile = urlOfMemberProfile.toString();
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

    document.put("_id", this._id);

    document.put("user_id", ((UserMongo) user).bsonFromPojo());
    document.put("meetupgroup_id",
        ((MeetUpGroupMongo) meetUpGroup).bsonFromPojo());

    document.put("memeberid", memeberId);
    document.put("joinedgroupon", joinedGroupOn);
    document.put("lastvisitedgroupon", lastVisitedGroupOn);
    document.put("lastattended", lastAttended);
    document.put("totalrsvps", totalRSVPs);
    document.put("rsvpedyes", rsvpedYes);
    document.put("rsvpedmaybe", rsvpedMaybe);
    document.put("rsvpedno", rsvpedNo);
    document.put("meetupsattended", meetupsAttended);
    document.put("noshows", noShows);
    document.put("intro", intro);
    document.put("photo", photo);
    document.put("assistantorganizer", assistantOrganizer);
    document.put("mailinglist", mailingList);
    document.put("urlofmemberprofile", urlOfMemberProfile);

    return document;
  }

  public void makePojoFromBson(DBObject aBson) {
    BasicDBObject basicDBObject = (BasicDBObject) aBson;

    _id = basicDBObject.getObjectId("_id");
    
    user = (UserMongo) basicDBObject.get("user_id");
    meetUpGroup = (MeetUpGroup) basicDBObject.get("meetupgroup_id");
    
    memeberId = basicDBObject.getString("memeberid");
    joinedGroupOn = basicDBObject.getDate("joinedgroupon");
    lastVisitedGroupOn = basicDBObject.getDate("lastvisitedgroupon");
    lastAttended = basicDBObject.getDate("lastattended");
    totalRSVPs = basicDBObject.getInt("totalrsvps");
    rsvpedYes = basicDBObject.getInt("rsvpedyes");
    rsvpedMaybe = basicDBObject.getInt("rsvpedmaybe");
    rsvpedNo = basicDBObject.getInt("rsvpedno");
    meetupsAttended = basicDBObject.getInt("meetupsAttended");
    noShows = basicDBObject.getInt("noshows");
    intro = basicDBObject.getBoolean("intro");
    photo = basicDBObject.getBoolean("photo");
    assistantOrganizer = basicDBObject.getBoolean("assistantorganizer");
    mailingList = basicDBObject.getString("mailinglist");
    urlOfMemberProfile = basicDBObject.getString("urlofmemberprofile");
  }

  @Override
  public User getUser() {
    return user;
  }

  @Override
  public MeetUpGroup getMeetUpGroup() {
    return meetUpGroup;
  }

  @Override
  public String getMemberId() {
    return memeberId;
  }

  @Override
  public Date getJoinedGroupOn() {
    return joinedGroupOn;
  }

  @Override
  public Date getLastVisitedGroupOn() {
    return lastVisitedGroupOn;
  }

  @Override
  public Date getLastAttended() {
    return lastAttended;
  }

  @Override
  public int getTotalRSVPs() {
    return totalRSVPs;
  }

  @Override
  public int getRSVPedYes() {
    return rsvpedYes;
  }

  @Override
  public int getRSVPedMaybe() {
    return rsvpedMaybe;
  }

  @Override
  public int getRSVPedNo() {
    return rsvpedNo;
  }

  @Override
  public int getMeetupsAttended() {
    return meetupsAttended;
  }

  @Override
  public int getNoShow() {
    return noShows;
  }

  @Override
  public boolean hasIntro() {
    return intro;
  }

  @Override
  public boolean hasPhoto() {
    return photo;
  }

  @Override
  public boolean isAssistantOrganizer() {
    return assistantOrganizer;
  }

  @Override
  public String getMailingList() {
    return mailingList;
  }

  @Override
  public URL getMemberProfileURL() {
    try {
      return new URL(urlOfMemberProfile);
    } catch (MalformedURLException e) {
      // We only have well formed URL coming from here.
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean containsField(String aField) {
    return (aField.equals("_id") || aField.equals("user_id")
        || aField.equals("meetupgroup_id") || aField.equals("memeberid")
        || aField.equals("joinedgroupon")
        || aField.equals("lastvisitedgroupon") || aField.equals("lastattended")
        || aField.equals("totalrsvps") || aField.equals("rsvpedyes")
        || aField.equals("rsvpedmaybe") || aField.equals("rsvpedno")
        || aField.equals("meetupsattended") || aField.equals("noshows")
        || aField.equals("intro") || aField.equals("photo")
        || aField.equals("assistantorganizer") || aField.equals("mailinglist") 
        || aField.equals("urlofmemberprofile"));
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
      case "user_id" :
        return user;
      case "meetupgroup_id" :
        return meetUpGroup;
      case "memeberid" :
        return memeberId;
      case "joinedgroupon" :
        return joinedGroupOn;
      case "lastvisitedgroupon" :
        return lastVisitedGroupOn;
      case "lastattended" :
        return lastAttended;
      case "totalrsvps" :
        return totalRSVPs;
      case "rsvpedyes" :
        return rsvpedYes;
      case "rsvpedmaybe" :
        return rsvpedMaybe;
      case "rsvpedno" :
        return rsvpedNo;
      case "meetupsattended" :
        return meetupsAttended;
      case "noshows" :
        return noShows;
      case "intro" :
        return intro;
      case "photo" :
        return photo;
      case "assistantorganizer" :
        return assistantOrganizer;
      case "mailinglist" :
        return mailingList;
      case "urlofmemberprofile" :
        return urlOfMemberProfile;
      default :
        return null;
    }
  }

  @Override
  public Set<String> keySet() {
    Set<String> set = new HashSet<String>();

    set.add("_id");
    set.add("user_id");
    set.add("meetupgroup_id");
    set.add("memeberid");
    set.add("joinedgroupon");
    set.add("lastvisitedgroupon");
    set.add("lastattended");
    set.add("totalrsvps");
    set.add("rsvpedyes");
    set.add("rsvpedmaybe");
    set.add("rsvpedno");
    set.add("meetupsattended");
    set.add("noshows");
    set.add("intro");
    set.add("photo");
    set.add("assistantorganizer");
    set.add("mailinglist");
    set.add("urlofmemberprofile");

    return set;
  }

  @Override
  public Object put(String aField, Object aObject) {
    switch (aField) {
      case "_id" :
        _id = (ObjectId) aObject;
        break;
      case "user_id" :
        user = (UserMongo) aObject;
        break;
      case "meetupgroup_id" :
        meetUpGroup = (MeetUpGroupMongo) aObject;
        break;
      case "memeberid" :
        memeberId = (String) aObject;
        break;
      case "joinedgroupon" :
        joinedGroupOn = (Date) aObject;
        break;
      case "lastvisitedgroupon" :
        lastVisitedGroupOn = (Date) aObject;
        break;
      case "lastattended" :
        lastAttended = (Date) aObject;
        break;
      case "totalrsvps" :
        totalRSVPs = (int) aObject;
        break;
      case "rsvpedyes" :
        rsvpedYes = (int) aObject;
        break;
      case "rsvpedmaybe" :
        rsvpedMaybe = (int) aObject;
        break;
      case "rsvpedno" :
        rsvpedNo = (int) aObject;
        break;
      case "meetupsattended" :
        meetupsAttended = (int) aObject;
        break;
      case "noshows" :
        noShows = (int) aObject;
        break;
      case "intro" :
        intro = (boolean) aObject;
        break;
      case "photo" :
        photo = (boolean) aObject;
        break;
      case "assistantorganizer" :
        assistantOrganizer = (boolean) aObject;
        break;
      case "mailinglist" :
        mailingList = (String) aObject;
        break;
      case "urlofmemberprofile" :
        urlOfMemberProfile = (String) aObject;
        break;
      default :
        return null;
    }
    
    return aObject;
  }

  @Override
  public void putAll(BSONObject aBson) {
    for( String key : aBson.keySet() ) {
      put( key, aBson.get( key ) );
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

    if (_id != null)
      map.put("_id", this._id);
    if (user != null)
      map.put("user", user);
    if (meetUpGroup != null)
      map.put("_id", meetUpGroup);
    if (memeberId != null)
      map.put("memeberid", memeberId);
    if (joinedGroupOn != null)
      map.put("joinedgroupon", joinedGroupOn);
    if (lastVisitedGroupOn != null)
      map.put("lastvisitedgroupon", lastVisitedGroupOn);
    if (lastAttended != null)
      map.put("lastattended", lastAttended);
    if (totalRSVPs != 0)
      map.put("totalrsvps", totalRSVPs);
    if (rsvpedYes != 0)
      map.put("rsvpedyes", rsvpedYes);
    if (rsvpedMaybe != 0)
      map.put("rsvpedmaybe", rsvpedMaybe);
    if (rsvpedNo != 0)
      map.put("rsvpedno", rsvpedNo);
    if (meetupsAttended != 0)
      map.put("meetupsattended", meetupsAttended);
    if (noShows != 0)
      map.put("noshows", noShows);

    map.put("intro", intro);
    map.put("photo", photo);
    map.put("assistantorganizer", assistantOrganizer);

    if (mailingList != null)
      map.put("mailinglist", mailingList);
    if (urlOfMemberProfile != null)
      map.put("urlofmemberprofile", urlOfMemberProfile.toString());

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
