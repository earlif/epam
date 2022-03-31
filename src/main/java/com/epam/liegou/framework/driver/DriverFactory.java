package com.epam.liegou.framework.driver;

import org.openqa.selenium.remote.RemoteWebDriver;

public interface DriverFactory {
  RemoteWebDriver getDriver();
}
