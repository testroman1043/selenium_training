package ru.stqa.training.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CloudGridTests {

  private WebDriver driver;
  public static final String USERNAME = "roman";
  public static final String AUTOMATE_KEY = "E";
  public static final String URL =
      "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


  @BeforeTest
  public void start() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setCapability("browserName", "chrome");
    chromeOptions.addArguments("--start-maximized");
    try {
      driver = new RemoteWebDriver(new URL(URL), chromeOptions);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @AfterTest
  public void stop() {
    driver.quit();
  }

  @Test
  public void test1() {
    driver.get("https://news.rambler.ru/");
    System.out.println(driver.getTitle());
  }

  @Test
  public void test2() {
    driver.get("https://sferum.ru");
    WebDriverWait wait = new WebDriverWait(driver, 5);
    System.out.println(driver.getTitle());
  }


}


