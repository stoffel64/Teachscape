package com.meet.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.meet.factories.MeetUpFactory;
import com.meet.factories.MeetUpFactoryBuilder;

public class MeetUpCSVReader {

  private File csvFile;
  private CSVFormat csvFormat = null;
  private MeetUpFactory factory;

  public MeetUpCSVReader(File aCsvFile, String factoryType) {
    csvFormat = CSVFormat.EXCEL.withHeader("Name", "User ID", "Title",
        "Member ID", "Company", "Joined Group on", "Last visited group on",
        "Last Attended", "Total RSVPs", "RSVPed Yes", "RSVPed Maybe",
        "RSVPed No", "Meetups attended", "No shows", "Intro", "Photo",
        "Assistant Organizer", "Mailing List", "URL of Member Profile");
    csvFile = aCsvFile;
    factory = MeetUpFactoryBuilder.getInstance(factoryType);
  }

  public void readInCsvFile() throws FileNotFoundException {
    Reader reader = new FileReader(csvFile);

    try {
      Iterable<CSVRecord> parser = csvFormat.parse(reader);
      // The csv file has always a header (assumption), we have to read
      // over the header line (first record).
      if (parser.iterator().hasNext()) {
        parser.iterator().next();
      }

      for (CSVRecord record : parser) {
        // setup the user groups
        String aURL = record.get("URL of Member Profile");
        String groupName = aURL.split("/")[3];
        factory.setupMeetUpGroup(groupName, groupName + "@meetUp.com");

        // setup the users
        factory.setupUser(record.get("Name"), record.get("User ID"),
            record.get("Title"), record.get("Company"));

        // setup user meet up group objects
        factory.setupUserMeetUpGroup(record.get("User ID"), groupName,
            record.get("Member ID"), record.get("Joined Group on"),
            record.get("Last visited group on"), record.get("Last Attended"),
            record.get("Total RSVPs"), record.get("RSVPed Yes"),
            record.get("RSVPed Maybe"), record.get("RSVPed No"),
            record.get("Meetups attended"), record.get("No shows"),
            record.get("Intro"), record.get("Photo"),
            record.get("Assistant Organizer"),
            record.get("Mailing List"), record.get("URL of Member Profile"));

      }
    } catch (IOException e) {
      System.err.println("We could not read the csv file.");
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          System.err.println("We could not close the file reader!");
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {

    File csvFile = new File("contacts.csv");

    if (!csvFile.exists()) {
      System.err.println("File with the name/path doe not exsit: "
          + csvFile.getAbsolutePath());
    }

    MeetUpCSVReader meetUpCSVReader = new MeetUpCSVReader(csvFile,
        MeetUpFactoryBuilder.MONGO_FACTORY);
    try {
      meetUpCSVReader.readInCsvFile();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
