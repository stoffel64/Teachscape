package com.meet.dao;

public interface QueryUserMeetUp {
  
  /**
   * Returns a list of companies ordered alphabetically specified by the 
   * a group name.
   * @param aGroupName
   * @return
   */
  String[] getAllCompaniesOfMeetUpGroup(String aGroupName);

}
