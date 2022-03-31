/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.liegou.framework;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GlobalVar {

  private GlobalVar() {}

  // time out
  public static final int WAIT_TIMEOUT_HIGH_S = 60;
  public static final int WAIT_TIMEOUT_MEDIUM_S = 30;
  public static final int WAIT_TIMEOUT_LOW_S = 8;
  // api path
  public static final String TEMPLATE_FILE_PATH = "src/main/resources/template/";
  public static final String FEATURE_FILE_PATH =
      "src/main/java/com/welab/automation/projects/wealth/features/api/";
  public static final String LOANS_FEATURE_FILE_PATH =
      "src/main/java/com/welab/automation/projects/loans/features/api/loans/functions/";
  public static final String TEST_DATA_FILE_PATH = "src/main/resources/api/testData/";
  public static final String RESPONSE_JSON_FILE_PATH = "src/main/resources/api/testData/output/";
  public static final String REQUEST_JSON_FILE_PATH = "src/main/resources/api/testData/input/";
  public static final String LOANS_REQUEST_JSON_FILE_PATH =
      "src/main/resources/api/testData/loans/";
  public static final String APP_TEST_DATA_PATH = "src/main/resources/app/testData/";
  public static final String RUN_CASE_NO = "src/main/resources/api/runCaseNo.txt";
  public static Map<String, String> HEADERS = new HashMap<>();
  public static Map<String, String> COOKIES = new HashMap<>();
  public static String ENV = "env";
  public static String PRODUCTION_ENV = "production";
//  public static final Faker faker = new Faker(new Locale("en", "US"));
  // Mobile Farm
  public static final String PROJECT_NAME = "welab";
  public static final String APPIUM_HUB = "mobilecloud.epam.com";
  public static final String MOBILE_FARM_API_URL = "https://mobilecloud.epam.com/billing/unit/welab/automation/api";
  public static final String DEVICE_JSON_PATH = "src/main/resources/clouddevices/devices.json";
  public static final String DEVICE_CAPS_JSON_PATH =
      "src/main/resources/clouddevices/devices_caps.json";
  public static final String MOBILE_FARM_API_SCHEMA =
      "src/main/resources/mobile_farm_api_schema.json";
  // Report
  public static final String TEST_REPORT_PATH = "TestReport/";
  public static final String ALLURE_RESULTS_PATH = "target/allure-results/";
  public static final String ALLURE_RESULTS_HISTORY_PATH = "target/allure-results/history";
  public static final String TARGET_LOG_FILE = "target/logs.txt";
  public static final String TARGET_SCREENSHOTS_PATH = "target/screenshots/";
  public static final String TARGET_RECORDING_PATH = "target/recordings/";
  public static Map<String, String> GLOBAL_VARIABLES = new HashMap<>();

  public static final String WEALTH_PATH = "/v1/wealth";
  public static final String PG_CONFIGFILENAME = "application.properties";

  public static final String RN_ACCOUNT_CONFIG_FILE =
      "/Users/peng_zhou/Documents/welab/welabBank/wealthsdk/src/config/config.sit.ts";
}
