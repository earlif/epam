package com.epam.liegou.projects.demo.steps;

import com.epam.liegou.framework.driver.BaseDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MobileHooks {
  private static final Logger logger = LoggerFactory.getLogger(MobileHooks.class);
  BaseDriver baseDriver;

  @Before
  public void beforeScenario(Scenario scenario) throws IOException {
    logger.info("* Start running scenario: {}", scenario.getName());
    this.baseDriver = new BaseDriver();
//    this.baseDriver.initMobileDriver();
    System.out.println(BaseDriver.getMobileDriver());
  }

  @After
  public void afterScenario(Scenario scenario) {
    logger.info("* End running scenario: {}", scenario.getName());
    BaseDriver.closeMobileDriver();
  }
}
