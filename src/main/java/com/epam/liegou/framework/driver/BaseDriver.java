package com.epam.liegou.framework.driver;

import com.epam.liegou.framework.GlobalVar;
import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BaseDriver {
  private static final Logger logger = LoggerFactory.getLogger(BaseDriver.class);
  private static RemoteWebDriver webDriver;
  private static RemoteWebDriver mobileDriver;
  private static URL url;

  private void setURL() throws IOException {
    String hub = GlobalVar.GLOBAL_VARIABLES.get("hub");
    url = new URL("http://" + hub + "/wd/hub");
  }

  public static URL getURL() {
    return url;
  }

  public static void closeDriver() {
    if (webDriver != null) {
      webDriver.quit();
      logger.info("The web driver is quit.");
    }
  }

  public static void closeMobileDriver() {
    if (mobileDriver != null) {
      mobileDriver.quit();
      logger.info("The mobile driver is quit.");
    }
  }

  static Scenario scenario;
  public Map<String, Object> testData = new HashMap<>();

  public Map<String, Object> getTestData() {
    return testData;
  }

  public void setTestData(Map<String, Object> testData) {
    this.testData = testData;
  }

  public static RemoteWebDriver getWebDriver() {
    return webDriver;
  }

  public static RemoteWebDriver getMobileDriver() {
    return mobileDriver;
  }

  public Scenario getScenario() {
    return scenario;
  }

  public void setScenario(Scenario scenario) {
    this.scenario = scenario;
  }


  public void initMobileDriver() throws IOException {
    DriverFactory driverFactory;
    setURL();
    String deviceType = System.getProperty("mobile").toUpperCase();

    driverFactory = new AndroidDriverFactory();

    mobileDriver = driverFactory.getDriver();
    logger.info("The {} driver is started.", deviceType);
  }

  private String getBrowserType() {
    String browser = System.getProperty("platform");
    if (browser == null) {
      return "chrome";
    }
    return browser;
  }
}
