package com.epam.liegou.framework.base;

import io.appium.java_client.MobileElement;
import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;



class BasePage {
  private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
  RemoteWebDriver driver;
  protected String pageName;
  protected static boolean IS_IOS;

  static {
    if (System.getProperty("mobile").contains("ios")) IS_IOS = true;
    else IS_IOS = false;
  }

  public static void logFail(String errorMsg, String pageName, WebElement element) {
    String logDetails = String.format("%s on %s | %s", errorMsg, pageName, element);
//    Allure.step(logDetails, Status.FAILED);
    logger.error(logDetails);
  }

  public static void logFail(String errorMsg, String pageName) {
    String logDetails = String.format("%s on %s", errorMsg, pageName);
//    Allure.step(logDetails, Status.FAILED);
    logger.error(logDetails);
  }

  public static void logFail(String errorMsg) {
//    Allure.step(errorMsg, Status.FAILED);
    logger.error(errorMsg);
  }

  public static void logPass(String passMsg, String pageName, WebElement element) {
    String logDetails = String.format("%s on %s | %s", passMsg, pageName, element);
//    Allure.step(logDetails, Status.PASSED);
    logger.info(logDetails);
  }

  public static void logPass(String passMsg, String pageName, By by) {
    String logDetails = String.format("%s on %s | %s", passMsg, pageName, by.toString());
//    Allure.step(logDetails, Status.PASSED);
    logger.info(logDetails);
  }

  public static void logPass(String passMsg, String pageName) {
    String logDetails = String.format("%s on %s", passMsg, pageName);
//    Allure.step(logDetails, Status.PASSED);
    logger.info(logDetails);
  }

  public static void logPass(String passMsg) {
//    Allure.step(passMsg, Status.PASSED);
    logger.info(passMsg);
  }

  public WebElement findElement(By by) {
    try {
      return driver.findElement(by);
    } catch (StaleElementReferenceException e) {
      logPass("StaleElementReferenceException, try again to find element!", pageName);
      return driver.findElement(by);
    } catch (Exception e) {
      logFail("Element was not found by " + by.toString(), pageName);
      throw e;
    }
  }

  public void clickElement(WebElement element) {
    try {
      element.click();
      logPass("Click element", pageName, element);
    } catch (Exception e) {
      logFail("Failed to Click element", pageName, element);
      throw e;
    }
  }

  public void clickLink(String linkName) {
    try {
      WebElement linkEle = driver.findElement(By.linkText(linkName));
      linkEle.click();
      String logDetails = String.format("Click the link %s", linkName);
      logPass(logDetails, pageName);
    } catch (Exception e) {
      String logDetails = String.format("Can not click the link %s", linkName);
      logFail(logDetails, pageName);
      throw e;
    }
  }

  public String getElementAttribute(WebElement element, String attribute) {
    String attributeValue = null;
    try {
      attributeValue = element.getAttribute(attribute);
      String logDetails = String.format("Get attribute %s value %s", attribute, attributeValue);
      logPass(logDetails, pageName, element);
    } catch (Exception e) {
      String errorMsg = String.format("Failed to get attribute %s", attribute);
      logFail(errorMsg, pageName, element);
    }
    return attributeValue;
  }

  public void clearInput(WebElement element) {
    try {
      element.click();
      element.clear();
      logPass("Clear input", pageName, element);
    } catch (Exception e) {
      String errorMsg = "Failed to clear input";
      logFail(errorMsg, pageName, element);
    }
  }

  public void andSendKeys(WebElement element, String txt) {
    try {
      element.sendKeys(txt);
      String logDetails = String.format("Set text: %s successfully", txt);
      logPass(logDetails, pageName, element);
    } catch (Exception e) {
      String logDetails = String.format("Failed to set text: %s", txt);
      logFail(logDetails, pageName, element);
    }
  }

  public void clearAndSendKeys(WebElement element, String txt) {
    try {
      clearInput(element);
      element.sendKeys(txt);
      String logDetails = String.format("Set text: %s successfully", txt);
      logPass(logDetails, pageName, element);
    } catch (Exception e) {
      String logDetails = String.format("Failed to set text: %s", txt);
      logFail(logDetails, pageName, element);
    }
  }

  public void selectByIndex(WebElement element, int index) {
    try {
      Select select = new Select(element);
      select.selectByIndex(index);
      String logDetails = String.format("Select by index: %d successfully", index);
      logPass(logDetails, pageName, element);
    } catch (Exception e) {
      String logDetails = String.format("Failed to select by index: %d", index);
      logFail(logDetails, pageName, element);
      throw e;
    }
  }

  public void selectByVisibleText(WebElement element, String text) {
    try {
      Select select = new Select(element);
      select.selectByVisibleText(text);
      String logDetails = String.format("Select by visible text: %s successfully", text);
      logPass(logDetails, pageName, element);
    } catch (Exception e) {
      String logDetails = String.format("Failed to select by visible text: %s", text);
      logFail(logDetails, pageName, element);
      throw e;
    }
  }

  public void selectMultipleByVisibleText(WebElement element, String options) {
    String[] optionsArray = options.split(",");
    try {
      Select select = new Select(element);
      if (select.isMultiple()) {
        for (String option : optionsArray) {
          select.selectByVisibleText(option);
        }
        String logDetails =
            String.format("Select multiple by visible text: %s successfully", options);
        logPass(logDetails, pageName, element);
      } else {
        String logDetails = "It is a single selector";
        logFail(logDetails, pageName, element);
      }
    } catch (Exception e) {
      String logDetails = String.format("Failed to select by visible text: %s", options);
      logFail(logDetails, pageName, element);
      throw e;
    }
  }

  public void selectByValue(WebElement element, String value) {
    try {
      Select select = new Select(element);
      select.selectByValue(value);
      String logDetails = String.format("Select by value: %s successfully", value);
      logPass(logDetails, pageName, element);
    } catch (Exception e) {
      String logDetails = String.format("Failed to select by value: %s", value);
      logFail(logDetails, pageName, element);
      throw e;
    }
  }

  public String getSelectedOption(WebElement element) {
    String optionText = null;
    try {
      Select select = new Select(element);
      optionText = select.getAllSelectedOptions().get(0).getText();
      String logDetails = String.format("Get selected option: %s successfully", optionText);
      logPass(logDetails, pageName, element);
    } catch (Exception e) {
      String logDetails = "Failed to get selected option";
      logFail(logDetails, pageName, element);
      throw e;
    }
    return optionText;
  }

  public List<String> getSelectedOptions(WebElement element) {
    try {
      Select select = new Select(element);
      List<WebElement> selectedOptions = select.getAllSelectedOptions();
      List<String> options = new ArrayList<>();
      for (WebElement selectedOption : selectedOptions) {
        String selectOption = selectedOption.getText();
        options.add(selectOption);
      }
      String logDetails = String.format("Get selected options: %s successfully", options);
      logPass(logDetails, pageName, element);
      return options;
    } catch (Exception e) {
      String logDetails = "Failed to get selected options";
      logFail(logDetails, pageName, element);
      return null;
    }
  }

  public String getElementText(WebElement element) {
    String text = null;
    try {
      text = element.getText();
      String logDetails = String.format("Get text: %s", text);
      logPass(logDetails, pageName, element);
    } catch (Exception e) {
      String logDetails = "Failed to get text";
      logFail(logDetails, pageName, element);
    }
    return text;
  }

  public boolean verifyElementExist(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (Exception e) {
      logger.info("{}: expect element({}) exists, but does not exist", pageName, element);
      return false;
    }
  }

  public boolean verifyElementExist(By by) {
    boolean flag = false;
    try {
      WebElement element = driver.findElement(by);
      flag = true;
      String logDetails = "Expect element exists";
      logPass(logDetails, pageName, element);
    } catch (Exception e) {
      String logDetails = "Expect element exists, but does not exist";
      logFail(logDetails, pageName);
    }
    return flag;
  }

  public boolean verifyElementNotExist(By by) {
    boolean flag = false;
    try {
      WebElement element = driver.findElement(by);
      String logDetails = "Expect element does not exist, But exists";
      logFail(logDetails, pageName, element);
    } catch (Exception e) {
      flag = true;
      String logDetails = "Expect element does not exist";
      logPass(logDetails, pageName);
    }
    return flag;
  }

  public boolean verifyContainsText(WebElement element, String expectedText, boolean ignoreCase) {
    boolean flag = false;
    try {
      String actualText = getElementText(element);
      if (ignoreCase) {
        expectedText = expectedText.toLowerCase();
        actualText = actualText.toLowerCase();
      }
      if (actualText.contains(expectedText)) {
        flag = true;
        String logDetails =
            String.format(
                "Element contains expected text: %s, actual text: %s", expectedText, actualText);
        logPass(logDetails, pageName, element);
      } else {
        String logDetails =
            String.format(
                "Element does not contain expected text: %s, actual text: %s",
                expectedText, actualText);
        logFail(logDetails, pageName, element);
      }
    } catch (Exception e) {
      String logDetails =
          String.format("Failed to verify element contains expected text: %s", expectedText);
      logFail(logDetails, pageName, element);
    }
    return flag;
  }

  public boolean verifyNotContainsText(
      WebElement element, String unexpectedText, boolean ignoreCase) {
    boolean flag = false;
    try {
      String actualText = getElementText(element);
      if (ignoreCase) {
        unexpectedText = unexpectedText.toLowerCase();
        actualText = actualText.toLowerCase();
      }
      if (actualText.contains(unexpectedText)) {
        String logDetails =
            String.format(
                "Element contains unexpected text: %s, actual text: %s",
                unexpectedText, actualText);
        logFail(logDetails, pageName, element);
      } else {
        flag = true;
        String logDetails =
            String.format(
                "Element does not contain unexpected text: %s, actual text: %s",
                unexpectedText, actualText);
        logPass(logDetails, pageName, element);
      }
    } catch (Exception e) {
      String logDetails =
          String.format("Failed to verify element contains unexpected text: %s", unexpectedText);
      logFail(logDetails, pageName, element);
    }
    return flag;
  }

  public WebElement waitUntilElementVisible(WebElement element, int... timeOut) {
    int actualTimeout = timeOut[0];
    WebElement el = null;
    try {
      WebDriverWait wait = new WebDriverWait(driver, actualTimeout);
      el = wait.until(ExpectedConditions.visibilityOf(element));
      String logDetails = String.format("Element is visible in %s seconds", actualTimeout);
      logPass(logDetails, pageName, element);
    } catch (Exception e) {
      String errorMsg =
          String.format("Failed to wait element visible in %s seconds", actualTimeout);
      logFail(errorMsg, pageName, element);
      throw e;
    }
    return el;
  }

  @SneakyThrows
  public void waitUntilElementDisappeared(MobileElement element) {
    final int maxTimes = 30;
    int count = 1;
    boolean exist_flat = true;
    while (exist_flat) {
      try {
        element.isDisplayed();
        Thread.sleep(1000);
        count++;
      } catch (Exception e) {
        logger.info("{} disappears in {} second(s)", element, count);
        exist_flat = false;
      }
      if (count >= maxTimes) {
        logger.info("{} is still exists in {} second(s)", element, count);
        break;
      }
    }
  }

  public WebElement waitUntilElementClickable(WebElement element, int... timeOut) {
    int actualTimeout = timeOut[0];
    WebElement el = null;
    try {
      WebDriverWait wait = new WebDriverWait(driver, actualTimeout);
      el = wait.until(ExpectedConditions.elementToBeClickable(element));
      String logDetails = String.format("Element is clickable in %s seconds", actualTimeout);
      logPass(logDetails, pageName, element);
    } catch (Exception e) {
      String errorMsg =
          String.format("Failed to wait element clickable in %s seconds", actualTimeout);
      logFail(errorMsg, pageName, element);
      throw e;
    }
    return el;
  }

  public boolean waitUntilElementSelectable(WebElement element, int... timeOut) {
    int actualTimeout = timeOut[0];
    Boolean isSelected = null;
    try {
      WebDriverWait wait = new WebDriverWait(driver, actualTimeout);
      isSelected = wait.until(ExpectedConditions.elementToBeSelected(element));
      String logDetails = String.format("Element is selectable in %s seconds", actualTimeout);
      logPass(logDetails, pageName, element);
    } catch (Exception e) {
      String errorMsg =
          String.format("Failed to wait element selectable in %s seconds", actualTimeout);
      logFail(errorMsg, pageName, element);
      throw e;
    }
    return isSelected;
  }

  public boolean isElementDisplayed(WebElement element, String elementText, int... timeOut) {
    int actualTimeout = timeOut[0];
    WebDriverWait wait = new WebDriverWait(driver, actualTimeout);
    Boolean isDisplayed = false;
    try {
      isDisplayed = wait.until(ExpectedConditions.textToBePresentInElement(element, elementText));
      String logDetails = String.format("Element is displayed in %s seconds", actualTimeout);
      logPass(logDetails, pageName, element);
    } catch (TimeoutException e) {
      String logDetails = String.format("Element is displayed in %s seconds", actualTimeout);
      logPass(logDetails, pageName, element);
      return false;
    }
    return isDisplayed;
  }

  public WebElement isElementByClickable(By locator, int... timeOut) {
    int actualTimeout = timeOut[0];
    WebDriverWait wait = new WebDriverWait(driver, actualTimeout);
    WebElement element = null;
    try {
      element = wait.until(ExpectedConditions.elementToBeClickable(locator));
      String logDetails = String.format("Element is clickable in %s seconds", actualTimeout);
      logPass(logDetails, pageName, element);
    } catch (TimeoutException e) {
      String logDetails = String.format("Element is not clickable in %s seconds", actualTimeout);
      logFail(logDetails, pageName, element);
      return null;
    }
    return element;
  }

  public Boolean isElementClickable(By locator, int... timeOut) {
    int actualTimeout = timeOut[0];
    WebDriverWait wait = new WebDriverWait(driver, actualTimeout);
    Boolean status = false;
    try {
      status =
          wait.until(
              new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver webDriver) {
                  WebElement element =
                      ExpectedConditions.visibilityOfElementLocated(locator).apply(driver);
                  try {
                    String logDetails =
                        String.format("Element is clickable in %s seconds", actualTimeout);
                    logPass(logDetails, pageName, element);
                    return element != null && element.isEnabled();
                  } catch (StaleElementReferenceException var4) {
                    String logDetails =
                        String.format("Element is not clickable in %s seconds", actualTimeout);
                    logFail(logDetails, pageName, element);
                    return false;
                  }
                }

                public String toString() {
                  return "element to be clickable: " + locator;
                }
              });
    } catch (Exception var) {
      String logDetails = String.format("Element is not clickable in %s seconds", actualTimeout);
      logFail(logDetails, pageName);
    }
    return status;
  }

  public Boolean isElementVisible(WebElement element, int... timeOut) {
    int actualTimeout = timeOut[0];
    WebDriverWait wait = new WebDriverWait(driver, actualTimeout);
    return wait.until(
        new Function<WebDriver, Boolean>() {
          @Override
          public Boolean apply(WebDriver webDriver) {
            WebElement ele = ExpectedConditions.visibilityOf(element).apply(driver);
            try {
              String logDetails = String.format("Element is visible in %s seconds", actualTimeout);
              logPass(logDetails, pageName, element);
              return ele != null && ele.isDisplayed() ? true : false;
            } catch (StaleElementReferenceException var4) {
              String logDetails =
                  String.format("Element is not visible in %s seconds", actualTimeout);
              logFail(logDetails, pageName, element);
              return false;
            }
          }

          public String toString() {
            return "element to be visible: " + element;
          }
        });
  }

  public WebElement isElementDisplayed(WebElement element, int... timeOut) {
    int actualTimeout = timeOut[0];
    WebDriverWait wait = new WebDriverWait(driver, actualTimeout);
    WebElement webElement = null;
    try {
      webElement = wait.until(ExpectedConditions.visibilityOf(element));
      String logDetails = String.format("Element is displayed in %s seconds", actualTimeout);
      logPass(logDetails, pageName, element);
    } catch (TimeoutException e) {
      String logDetails = String.format("Element is not displayed in %s seconds", actualTimeout);
      logFail(logDetails, pageName, element);
    }
    return webElement;
  }

  public Boolean isElementNotVisible(WebElement element, int... timeOut) {
    int actualTimeout = timeOut[0];
    WebDriverWait wait = new WebDriverWait(driver, actualTimeout);
    Boolean invisibleTag = null;
    try {
      invisibleTag = wait.until(ExpectedConditions.invisibilityOf(element));
      String logDetails = String.format("Element is visible in %s seconds", actualTimeout);
      logPass(logDetails);
    } catch (TimeoutException e) {
      String logDetails = String.format("Element is not clickable in %s seconds", actualTimeout);
      logFail(logDetails);
      return false;
    }
    return invisibleTag;
  }

  public Boolean isElementVisible(MobileElement element, int... timeOut) {
    int actualTimeout = timeOut[0];
    WebDriverWait wait = new WebDriverWait(driver, actualTimeout);
    return wait.until(
        new Function<WebDriver, Boolean>() {
          @Override
          public Boolean apply(WebDriver webDriver) {
            try {
              WebElement targetElement = ExpectedConditions.visibilityOf(element).apply(driver);
              String logDetails =
                  String.format("Element is displayed in %s seconds", actualTimeout);
              logPass(logDetails, pageName, targetElement);
              return element != null && element.isDisplayed();
            } catch (StaleElementReferenceException var4) {
              String logDetails =
                  String.format("Element is not displayed in %s seconds", actualTimeout);
              logFail(logDetails, pageName, element);
              return false;
            }
          }

          public String toString() {
            return "element to be visible : ";
          }
        });
  }

//  public Boolean isAllElementsVisible(List<MobileElement> elements, int... timeOut) {
//    int actualTimeout = timeOut[0];
//    WebDriverWait wait = new WebDriverWait(driver, actualTimeout);
//    return wait.until(
//        new Function<WebDriver, Boolean>() {
//          @Override
//          public Boolean apply(WebDriver webDriver) {
//            try {
//              boolean displayTag =
//                  elements.stream()
//                      .allMatch(
//                          ele -> ExpectedConditions.visibilityOf(ele).apply(driver).isDisplayed());
//              String logDetails =
//                  String.format("Elements are displayed in %s seconds", actualTimeout);
//              logPassElements(logDetails, pageName, elements);
//              return displayTag;
//            } catch (StaleElementReferenceException var4) {
//              String logDetails =
//                  String.format("Elements are not displayed in %s seconds", actualTimeout);
//              logFailElements(logDetails, pageName, elements);
//              return false;
//            }
//          }
//
//          public String toString() {
//            return "elements to be visible : ";
//          }
//        });
//  }

  // if device is iOS 15, then need to use appium 1.22
  // if device is iOS 14.x or lower version, need to use appium 1.21
//  public void takeScreenshot(String screenshotName) {
//    TakesScreenshot driver = this.driver;
//    File screenFile = driver.getScreenshotAs(OutputType.FILE);
//    File file = new File(TARGET_SCREENSHOTS_PATH + screenshotName + ".png");
//    try {
//      FileUtils.copyFile(screenFile, file);
//      logPass("Screenshot is saved successfully: " + file.getAbsolutePath());
//    } catch (IOException e) {
//      logFail("Failed to save screenshot: " + e.getLocalizedMessage());
//    }
//  }

  public boolean verifyAllElementExist(List<WebElement> elements) {
    try {
      boolean flag = true;
      List<Boolean> AllE = new ArrayList<>();
      Iterator<WebElement> it = elements.iterator();
      while (it.hasNext()) {
        AllE.add(it.next().isDisplayed());
      }
      if (AllE.contains(false)) {
        flag = false;
      }
      return flag;
    } catch (Exception e) {
      logger.info("{}: expect elements({}) exists, but does not exist", pageName, elements);
      return false;
    }
  }
}
