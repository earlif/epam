package com.epam.liegou.framework.utils;

import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import io.qameta.allure.ReportGenerator;
import io.qameta.allure.model.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.epam.liegou.framework.GlobalVar.*;

public class Utils {
  private static final Logger logger = LoggerFactory.getLogger(Utils.class);

  public static void generateAllureReport(String out) throws IOException {

    Thread.currentThread().setName("MainTest");

    String properties =
        "Environment="
            + "local".toUpperCase()
            + "\n"
            + "Platform="
            + "android".toUpperCase();
    generateAllureEnvFile(properties);

//    copyAllureHistory();
    ReportGenerator reportGenerator = new ReportGenerator(new AllureConfig().useDefault().build());

    Path outPut = Paths.get(TEST_REPORT_PATH + out);
    Path from = Paths.get(ALLURE_RESULTS_PATH);

    reportGenerator.generate(outPut, from);
    logger.info(
        "Report generate to " + Paths.get(TEST_REPORT_PATH + out, "index.html").toAbsolutePath());
  }

  private static void generateAllureEnvFile(String properties) {
    String allurepath = ALLURE_RESULTS_PATH + "environment.properties";
    writeObj(allurepath, properties);
  }

  public static boolean copyFile(String sourceFileName, String targetFileName) {
    File source = new File(sourceFileName);
    File target = new File(targetFileName);
    if (!source.exists() || !source.isFile()) {
      logger.error("Source {} does not exist or is not a file", sourceFileName);
      return false;
    }
    if (target.exists()) {
      target.delete();
    }
    boolean flag = false;
    try {
      Files.copy(source.toPath(), target.toPath());
      flag = target.exists();
      logger.info("Source file {} is copied to {}", sourceFileName, targetFileName);
    } catch (Exception e) {
      logger.error("Can not copy the file {} to {}", sourceFileName, targetFileName);
    }
    return flag;
  }

  public static void writeObj(String fileName, String content) {
    File file = new File(fileName);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(content);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
//
//  private static void copyAllureHistory() {
//    File testReport = new File(TEST_REPORT_PATH);
//    if (testReport.exists() && testReport.isDirectory()) {
//      File[] resultsList = testReport.listFiles();
//      if (resultsList.length > 0) {
//        String newerDateTime = getFileDirectoryCreatedDateTime(resultsList[0]);
//        File newerFile = resultsList[0];
//        for (int i = 1; i < resultsList.length; i++) {
//          String tmpDateTime = getFileDirectoryCreatedDateTime(resultsList[i]);
//          boolean isNewer = TimeUtils.checkNewerDateTime(newerDateTime, tmpDateTime);
//          if (isNewer) {
//            newerDateTime = tmpDateTime;
//            newerFile = resultsList[i];
//          }
//        }
//        String historyFolder = newerFile.getPath() + "/history";
//        copyDirectory(historyFolder, ALLURE_RESULTS_HISTORY_PATH);
//      }
//    }
//  }
//
//  /**
//   * To clear TestReport directory
//   *
//   * @param capacity the number of test report folder to be saved
//   */
//  public static void clearTestReport(int capacity) {
//    File dirFile = new File("TestReport");
//    if (dirFile.exists()) {
//      File[] fileList = new File("TestReport").listFiles();
//      // listFiles() does not guarantee any order, need to reorder to ensure the oldest report is
//      // deleted.
//      Arrays.sort(fileList);
//      int actualNumber = fileList.length;
//      if (fileList != null && actualNumber > capacity) {
//        logger.info("Begin Clearing TestReport...");
//        // after this run, one more report will be generated, so one more report need to deleted
//        for (int i = 0; i < actualNumber - capacity + 1; i++) {
//          deleteFolderOrFile(fileList[i].getAbsolutePath());
//        }
//        logger.info("Clearing TestReport is done.");
//      }
//    }
//  }

  public static void logFail(String errorMsg, String pageName, WebElement element) {
    String logDetails = String.format("%s on %s | %s", errorMsg, pageName, element);
    Allure.step(logDetails, Status.FAILED);
    logger.error(logDetails);
  }

  public static void logFailElements(
      String errorMsg, String pageName, List<MobileElement> elements) {
    String logDetails = String.format("%s on %s | %s", errorMsg, pageName, elements);
    Allure.step(logDetails, Status.FAILED);
    logger.error(logDetails);
  }

  public static void logPassElements(
      String passMsg, String pageName, List<MobileElement> elements) {
    String logDetails = String.format("%s on %s | %s", passMsg, pageName, elements);
    Allure.step(logDetails, Status.PASSED);
    logger.info(logDetails);
  }

  public static void logFail(String errorMsg, String pageName) {
    String logDetails = String.format("%s on %s", errorMsg, pageName);
    Allure.step(logDetails, Status.FAILED);
    logger.error(logDetails);
  }

  public static void logFail(String errorMsg) {
    Allure.step(errorMsg, Status.FAILED);
    logger.error(errorMsg);
  }

  public static void logPass(String passMsg, String pageName, WebElement element) {
    String logDetails = String.format("%s on %s | %s", passMsg, pageName, element);
    Allure.step(logDetails, Status.PASSED);
    logger.info(logDetails);
  }

  public static void logPass(String passMsg, String pageName, By by) {
    String logDetails = String.format("%s on %s | %s", passMsg, pageName, by.toString());
    Allure.step(logDetails, Status.PASSED);
    logger.info(logDetails);
  }

  public static void logPass(String passMsg, String pageName) {
    String logDetails = String.format("%s on %s", passMsg, pageName);
    Allure.step(logDetails, Status.PASSED);
    logger.info(logDetails);
  }

  public static void logPass(String passMsg) {
    Allure.step(passMsg, Status.PASSED);
    logger.info(passMsg);
  }
}
