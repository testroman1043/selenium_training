package ru.stqa.training.selenium;

import java.util.List;
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BrowserLogsTests {

  private WebDriver driver;

  @BeforeTest
  public void start() {
    ChromeOptions chromopt = new ChromeOptions();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.BROWSER, Level.ALL);
    logPrefs.enable(LogType.DRIVER, Level.ALL);
    logPrefs.enable(LogType.CLIENT, Level.ALL);
    chromopt.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    driver = new ChromeDriver(chromopt);
    login();
  }

  @AfterTest
  public void stop() {
    driver.quit();
  }

  private void login() {
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

  @Test
  public void testBrowserLogs() {
    driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
    int logs1 = driver.manage().logs().get(LogType.BROWSER).getAll().size();
    int itemsListSize = driver.findElements(
        By.xpath("//td/a[contains(@href,'edit_product') and not(contains(@title,'Edit'))]")).size();
    for (int i = 0; i < itemsListSize; i++) {
      driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
      List<WebElement> itemsList = driver.findElements(
          By.xpath("//td/a[contains(@href,'edit_product') and not(contains(@title,'Edit'))]"));
      itemsList.get(i).click();
      List<LogEntry> logEntries = driver.manage().logs().get(LogType.BROWSER).getAll();
      for (LogEntry log : logEntries) {
        System.out.println(log);
      }
      Assert.assertEquals(logs1, logEntries.size());
    }
  }
}


