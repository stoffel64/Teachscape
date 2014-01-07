package com.meet.dao;

public interface MeetUpGroup {

  /**
   * Returns the group name.
   * @return
   */
  String getGroupName();
  
  /** 
   * Set a new group name.
   * @param aGroupName
   */
  void setGroupName(final String aGroupName);
  
  /**
   * Returns the current mailing list email for this group.
   * @return
   */
  String getMailingListEmail();
}
