package ru.stqa.training.selenium;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StickerTests {

  private WebDriver driver;


  @BeforeTest
  public void setUp() {
    driver = new ChromeDriver();
    login();
  }

  private void login() {
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

  @Test
  public void testStickers() {
    driver.get("http://localhost/litecart/en/");
    List<WebElement> items = driver
        .findElements(By.xpath("//li[contains(@class,'product')]"));
    for (WebElement item : items) {
      List<WebElement> itemStickers = item
          .findElements(By.xpath(".//div[contains(@class,'sticker')]"));
      Assert.assertEquals(itemStickers.size(), 1,
          "Неправильное количество стикеров у товара. " +
              item.findElement(By.xpath(".//a[@class='link']")).getAttribute("title"));
    }
  }

  @AfterTest
  public void stop() {
    driver.quit();
  }

}
