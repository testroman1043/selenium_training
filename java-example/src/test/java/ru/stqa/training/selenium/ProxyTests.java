package ru.stqa.training.selenium;


import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProxyTests {

  private WebDriver driver;

  @BeforeTest
  public void start() {
    String ip = System.getenv("MY_IP");
    Proxy proxy = new Proxy();
    proxy.setHttpProxy(ip + ":8888");
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setCapability("proxy", proxy);
    chromeOptions.setAcceptInsecureCerts(true);
    driver = new ChromeDriver();
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
    System.out.println(driver.getTitle());
  }


}


