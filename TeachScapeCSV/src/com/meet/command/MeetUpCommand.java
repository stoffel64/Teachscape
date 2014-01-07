package com.meet.command;

import java.io.File;
import java.io.FileNotFoundException;

import com.meet.csv.MeetUpCSVReader;
import com.meet.dao.QueryUserMeetUp;
import com.meet.factories.MeetUpFactory;
import com.meet.factories.MeetUpFactoryBuilder;

public class MeetUpCommand {

  public static final String COMPANY_COMMAND = "companies";

  public static final String LOAD_CSV_COMMAND = "csv";

  public static final String GROUP_NAME_PARAMETER = "group";

  public static final String FILE_PATH = "file";

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Command for help.");
      return;
    }

    String command = args[0];
    String parameter = args[1];

    if (command.equals(LOAD_CSV_COMMAND) && !parameter.isEmpty()) {
      loadCsv(parameter);
    }

    if (command.equals(COMPANY_COMMAND) && !parameter.isEmpty()) {
      queryCompanies(args);
    }
  }

  private static void queryCompanies(String[] args) {
    if (args.length == 3 && args[1].equals(GROUP_NAME_PARAMETER)) {
      MeetUpFactory factory = MeetUpFactoryBuilder
          .getInstance(MeetUpFactoryBuilder.MONGO_FACTORY);

      QueryUserMeetUp queryUserMeetUpGroup = factory
          .setupQueryUserMeetUpGroup();

      String[] companyNames = queryUserMeetUpGroup
          .getAllCompaniesOfMeetUpGroup(args[2]);

      System.out.println("All companies:");
      for (String companyName : companyNames) {
        System.out.println(companyName);
      }

    } else {
      System.err.println("You have to specify the meet up group name.");
    }
  }

  private static void loadCsv(String parameter) {
    File csvFile = new File(parameter);
    if (!csvFile.exists()) {
      System.err.println("The file with the name [" + parameter
          + "] does not exists.");
      System.exit(0);
    }

    MeetUpCSVReader meetUpCSVReader = new MeetUpCSVReader(csvFile,
        MeetUpFactoryBuilder.MONGO_FACTORY);
    try {
      meetUpCSVReader.readInCsvFile();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    System.out.println("Successfully loaded csv file: ["
        + csvFile.getAbsolutePath() + "].");
  }

}
