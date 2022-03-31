/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.liegou.projects;

import static com.epam.liegou.framework.GlobalVar.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.epam.liegou.framework.utils.Utils.copyFile;
import static com.epam.liegou.framework.utils.Utils.generateAllureReport;

public class TestRunner extends AbstractTestNGCucumberTests {

  @BeforeSuite(alwaysRun = true)
  public void setUp() {
    System.out.println("Here is @BeforeSuite");
  }

  @AfterSuite(alwaysRun = true)
  public static void tearDown() throws IOException {
    LocalDateTime now = LocalDateTime.now();
    String path = DateTimeFormatter.ofPattern("yyy-MM-dd-HH-mm-ss").format(now);
    generateAllureReport(path);
    copyFile(TARGET_LOG_FILE, TEST_REPORT_PATH + path + "/logs.txt");
    System.out.println("Here is @AfterSuite at " + path);
  }
}
