package com.meet.dao;

import java.util.List;

public interface User {
  /**
   * Returns the current user id;
   * @return
   */
  String getUserId();
  
  /**
   * Set a new user id. It will replace the old one.
   * @param aUserId
   */
  void setUserId(String aUserId);
  
  /**
   * Returns the current user name.
   * @return
   */
  String getUserName();
  
  /**
   * Sets a new user name. It will 
   * @param aUserName
   */
  void setUserName(final String aUserName);
  
  /**
   * Returns the users title.
   * @return
   */
  String getTitle();
  
  /**
   * sets a new user tile. It will replace the old user title.
   * @param aTilte
   */
  void setTitle(final String aTilte);
    
  /**
   * Returns the current company name.
   * @return
   */
  String getCompany();
  
  /** 
   * Sets a new company name. It will replace the old company name.
   * @param aCompany
   */
  void setCompany(final String aCompany);
  
  /** 
   * Adds a new <code>UserMeetUpGroup</code> to the list of user meet up groups.
   * If the <code>UserMeetUpGroup</code> already exists then we do nothing and 
   * just return the existing <code>UserMeetUpGroup</code>.
   * @param aUserMeetUpGroup
   * @return
   */
  //UserMeetUpGroup addUserMeetUpGroup(final UserMeetUpGroup aUserMeetUpGroup);
  
  /**
   * Find a <code>UserMeetUpGroup</code> specified by the Group Meet Up name.
   * @param aGroupName
   * @return
   */
  UserMeetUpGroup findUserMeetUpGroup(final String aGroupName);
  
  /**
   * Returns a list of all <code>UserMeetUpdGroup<code> objects associated 
   * to this user.
   * @return
   */
  List<UserMeetUpGroup> getUserMeetUpGroups();
}
