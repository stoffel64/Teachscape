package com.meet.dao;

import java.net.URL;
import java.util.Date;

public interface UserMeetUpGroup {
  
  User getUser();
  
  MeetUpGroup getMeetUpGroup();
  
  String getMemberId();
  
  Date getJoinedGroupOn();
  
  Date getLastVisitedGroupOn();
  
  Date getLastAttended();
  
  int getTotalRSVPs();
  
  int getRSVPedYes();
  
  int getRSVPedMaybe();
  
  int getRSVPedNo();
  
  int getMeetupsAttended();
  
  int getNoShow();
  
  boolean hasIntro();
  
  boolean hasPhoto();
  
  boolean isAssistantOrganizer();
  
  String getMailingList();
  
  URL getMemberProfileURL();
}
