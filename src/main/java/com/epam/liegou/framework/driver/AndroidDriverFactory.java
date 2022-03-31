package com.epam.liegou.framework.driver;

import com.epam.liegou.framework.GlobalVar;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class AndroidDriverFactory implements DriverFactory {
  @Override
  public RemoteWebDriver getDriver() {

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("appPackage", "welab.bank.sit");
    capabilities.setCapability("appActivity", "com.welabfrontend.MainActivity");
//    capabilities.setCapability(DEVICE_NAME, "Android SDK built for x86");
    capabilities.setCapability(PLATFORM_NAME, "Android");

    capabilities.setCapability(AUTOMATION_NAME, "UiAutomator2");
    capabilities.setCapability(NO_RESET, true);
    capabilities.setCapability(PLATFORM_VERSION, "10");

    RemoteWebDriver driver;

    try {
      URL url = new URL("http://0.0.0.0:4723/wd/hub");
      driver = new AndroidDriver(url, capabilities);
    } catch (Exception e) {
      System.out.println("init driver failed:" + e.getMessage());
      driver = null;
    }

    return driver;
  }
}
