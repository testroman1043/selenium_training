package ru.stqa.training.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class GridTests {


  private WebDriver driver;

  @BeforeTest
  public void start() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setCapability("browserName", "chrome");
//    chromeOptions.setCapability("platform", "WIN10");

    try {
      driver = new RemoteWebDriver(new URL("http://ip:4444/wd/hub"), chromeOptions);
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
    driver.get("https://ya.ru/");
    System.out.println(driver.getTitle());
  }
}



