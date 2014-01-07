package com.meet.factories;

import com.meet.dao.QueryUserMeetUp;


public interface MeetUpFactory {
  
  void setupUser(String aName, String aUserID, String aTitle, String aCompany);
  
  void setupUserMeetUpGroup(String aUserId, String aGroupName, String aMemberID, 
      String aJoinedGroupOn, String aLastVisitedGroupOn, String aLastAttended,
      String aTotalRSVPs, String aRSVPedYes,
      String aRSVPedMaybe, String aRSVPedNo,
      String aMeetupsAttended, String aNoShows,
      String aIntro, String aPhoto,
      String aAssistantOrganizer, 
      String aMailingList, String aURLOfMemberProfile);
  
  void setupMeetUpGroup(String aGroupName, String aMailingListEmail);
  
  QueryUserMeetUp setupQueryUserMeetUpGroup();
}
