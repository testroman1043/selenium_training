package ru.stqa.training.selenium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CountryGeoOrderTests {

  private WebDriver driver;
  
  @BeforeTest
  public void setUp() {
    driver = new ChromeDriver();
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
  public void testCountryOrder() {
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    List<WebElement> countries = driver
        .findElements(By.xpath("//tr[@class='row']//a[not(contains(@title,'Edit'))]"));

    for (int i = 0; i < countries.size() - 1; i++) {
      String countryA = countries.get(i).getText();
      String countryB = countries.get(i + 1).getText();
      int diff = countryA.compareTo(countryB);
      Assert.assertTrue(diff <= 0, countryA + " is before " + countryB);
    }
  }

  @Test
  public void testCountryZones() {
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    List<WebElement> countries = driver
        .findElements(By.xpath("//tr[@class='row']//a[not(contains(@title,'Edit'))]"));

    List<String> countriesWithZoneLinks = new ArrayList<>();
    for (WebElement country : countries) {
      String qtyZones = country.findElement(By.xpath("./../following-sibling::td[1]")).getText();
      if (!"0".equals(qtyZones)) {
        countriesWithZoneLinks.add(country.getAttribute("href"));
      }
    }
    for (String link : countriesWithZoneLinks) {
      driver.get(link);
      List<WebElement> zones = driver
          .findElements(By.xpath("//input[contains(@name,'zones') and contains(@name,'name')] "));

      for (int i = 0; i < zones.size() - 1; i++) {
        String zoneA = zones.get(i).getAttribute("value");
        String zoneB = zones.get(i + 1).getAttribute("value");
        int diff = zoneA.compareTo(zoneB);
        Assert.assertTrue(diff <= 0, zoneA + " is before " + zoneB);
      }
    }
  }

  @Test
  public void testGeoZones() {
    driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
    List<WebElement> geoZones = driver
        .findElements(By.xpath("//tr[@class='row']//a[not(contains(@title,'Edit'))]"));
    List<String> geoZoneLinks = new ArrayList<>();
    for (WebElement page : geoZones) {
      geoZoneLinks.add(page.getAttribute("href"));
    }
    for (String link : geoZoneLinks) {
      driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
      String xpath =
          "//tr[@class='row']/td/a[not(contains(@title,'Edit')) and @href='" + link + "']";
      List<WebElement> searchResult = driver.findElements(By.xpath(xpath));
      Assert.assertEquals(searchResult.size(), 1,
          "не найдена ссылка с xpath " + xpath);
      WebElement page = searchResult.get(0);
      page.click();
      List<WebElement> zones = driver.findElements(
          By.xpath("//select[contains(@name,'zone_code')]/option[@selected='selected']"));
      for (int i = 0; i < zones.size() - 1; i++) {
        String zoneA = zones.get(i).getText();
        String zoneB = zones.get(i + 1).getText();
        int diff = zoneA.compareTo(zoneB);
        Assert.assertTrue(diff <= 0, zoneA + " is before " + zoneB);
      }
    }
  }
}
