package ru.stqa.training.selenium;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PageTests {

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
  public void testH1OnEachMenuPage() {
    List<WebElement> leftPages = driver.findElements(By.xpath("//li[@id='app-']/a"));
    List<String> pageLinks = new ArrayList<>();
    for (WebElement page : leftPages) {
      pageLinks.add(page.getAttribute("href"));
    }

    for (String link : pageLinks) {
      String xpath = "//li[@id='app-']/a[@href='" + link + "']";
      List<WebElement> searchResult = driver.findElements(By.xpath(xpath));
      Assert.assertEquals(searchResult.size(), 1,
          "не найдена ссылка с xpath " + xpath);
      WebElement page = searchResult.get(0);
      page.click();
      Assert.assertFalse(driver.findElements(By.xpath("//h1")).isEmpty());

      // Проход по вложенным пунктам
      List<WebElement> subPages = driver.findElements(By.xpath("//ul[@class='docs']/li/a"));
      List<String> subLinks = new ArrayList<>();
      for (WebElement subPage : subPages) {
        subLinks.add(subPage.getAttribute("href"));
      }
      for (String subLink : subLinks) {
        xpath = "//ul[@class='docs']/li/a[@href='" + subLink + "']";
        searchResult = driver.findElements(By.xpath(xpath));
        Assert.assertEquals(searchResult.size(), 1,
            "не найдена ссылка с xpath " + xpath);
        WebElement subPage = searchResult.get(0);
        subPage.click();
        Assert.assertFalse(driver.findElements(By.xpath("//h1")).isEmpty());
      }
    }
  }


  @AfterTest
  public void stop() {
    driver.quit();
  }
}