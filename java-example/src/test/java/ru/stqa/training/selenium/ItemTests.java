package ru.stqa.training.selenium;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import java.awt.Color;

public class ItemTests {

  private final WebDriver driver;

  public ItemTests(WebDriver driver) {
    this.driver = driver;
  }


  @BeforeClass
  public void setUp() {
    login();
  }

  @AfterClass
  public void stop() {
    driver.quit();
  }

  private void login() {
    driver.get("http://localhost/litecart/admin/");
    driver.manage().window().setSize(new Dimension(1000, 1000));
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

  @Test
  public void testTextsMatching() {
    driver.get("http://localhost/litecart/en/");
    List<WebElement> campaignItems = driver
        .findElements(By.xpath("//div[@id='box-campaigns']//a[@class='link']"));
    WebElement firstItem = campaignItems.get(0);
    String itemText = firstItem.getAttribute("title");
    firstItem.click();
    String itemPageText = driver.findElement(By.xpath("//h1")).getText();
    Assert.assertEquals(itemText, itemPageText);
  }

  @Test
  public void testPricesMatching() {
    driver.get("http://localhost/litecart/en/");
    List<WebElement> campaignItems = driver
        .findElements(By.xpath("//div[@id='box-campaigns']//a[@class='link']"));
    WebElement firstItem = campaignItems.get(0);
    String itemRegularPrice = firstItem.findElement(By.xpath(".//s[@class='regular-price']"))
        .getText();
    String itemCampaignPrice = firstItem.findElement(By.xpath(".//strong[@class='campaign-price']"))
        .getText();
    firstItem.click();
    String itemPageRegularPrice = driver.findElement(By.xpath("//s[@class='regular-price']"))
        .getText();
    String itemPageCampaignPrice = driver.findElement(By.xpath("//strong[@class='campaign-price']"))
        .getText();
    Assert.assertEquals(itemRegularPrice, itemPageRegularPrice,
        "regular prices are not equals " + itemRegularPrice + " and " + itemPageRegularPrice);
    Assert.assertEquals(itemCampaignPrice, itemPageCampaignPrice,
        "campaign prices are not equals " + itemCampaignPrice + " and " + itemPageCampaignPrice);
  }


  @Test
  public void testRegularPricesStyleAndColor() {
    driver.get("http://localhost/litecart/en/");
    List<WebElement> campaignItems = driver
        .findElements(By.xpath("//div[@id='box-campaigns']//a[@class='link']"));
    WebElement firstItem = campaignItems.get(0);
    String itemRegularPriceColor = firstItem.findElement(By.xpath(".//s[@class='regular-price']"))
        .getCssValue("color");
    Color color = org.openqa.selenium.support.Color.fromString(itemRegularPriceColor).getColor();
    String itemRegularPriceStyle = firstItem.findElement(By.xpath(".//s[@class='regular-price']"))
        .getCssValue("text-decoration-line");
    Assert.assertTrue(color.getGreen() == color.getBlue() && color.getGreen() == color.getRed(),
        "Regular price color is not grey, but : " + itemRegularPriceColor);
    Assert.assertEquals(itemRegularPriceStyle, "line-through",
        "regular price style is not line-through, but " + itemRegularPriceStyle);

    firstItem.click();
    String itemPageRegularPriceStyle = driver.findElement(By.xpath("//s[@class='regular-price']"))
        .getCssValue("text-decoration-line");
    String itemPageRegularPriceColor = driver.findElement(By.xpath("//s[@class='regular-price']"))
        .getCssValue("color");
    Assert.assertEquals(itemPageRegularPriceStyle, "line-through",
        "regular price style on item page is not line-through, but " + itemPageRegularPriceStyle);

    Color colorPage = org.openqa.selenium.support.Color.fromString(itemPageRegularPriceColor)
        .getColor();
    Assert.assertTrue(color.getGreen() == color.getBlue() && color.getGreen() == color.getRed(),
        "Regular price color on item page is not grey, but : " + itemPageRegularPriceColor);
  }


  @Test
  public void testCampaignPriceStyleAndColor() {
    driver.get("http://localhost/litecart/en/");
    List<WebElement> campaignItems = driver
        .findElements(By.xpath("//div[@id='box-campaigns']//a[@class='link']"));
    WebElement firstItem = campaignItems.get(0);
    String itemCampaignPriceColor = firstItem
        .findElement(By.xpath(".//strong[@class='campaign-price']"))
        .getCssValue("color");
    String itemCampaignPriceStyle = firstItem
        .findElement(By.xpath(".//strong[@class='campaign-price']"))
        .getCssValue("font-weight");
    Color color = org.openqa.selenium.support.Color.fromString(itemCampaignPriceColor).getColor();
    Assert.assertTrue(color.getGreen() == 0 && color.getBlue() == 0,
        "Campaign price color is not red, but : " + itemCampaignPriceColor);
    Assert
        .assertTrue("700".equals(itemCampaignPriceStyle) || "bold".equals(itemCampaignPriceStyle),
            "campaign price is not bold, but " + itemCampaignPriceStyle);

    firstItem.click();
    String itemPageCampaignPriceStyle = driver
        .findElement(By.xpath("//strong[@class='campaign-price']"))
        .getCssValue("font-weight");
    String itemPageCampaignPriceColor = driver
        .findElement(By.xpath("//strong[@class='campaign-price']"))
        .getCssValue("color");
    Assert
        .assertTrue(
            "700".equals(itemPageCampaignPriceStyle) || "bold".equals(itemPageCampaignPriceStyle),
            "campaign price is not bold, but " + itemPageCampaignPriceStyle);
    Color colorPage = org.openqa.selenium.support.Color.fromString(itemPageCampaignPriceColor)
        .getColor();
    Assert.assertTrue(color.getGreen() == 0 && color.getBlue() == 0,
        "Campaign price color on item page is not red, but : " + itemCampaignPriceColor);
  }

  @Test
  public void testPriceSize() {
    driver.get("http://localhost/litecart/en/");
    List<WebElement> campaignItems = driver
        .findElements(By.xpath("//div[@id='box-campaigns']//a[@class='link']"));
    WebElement firstItem = campaignItems.get(0);
    String itemRegularPriceFontSize = firstItem
        .findElement(By.xpath(".//s[@class='regular-price']"))
        .getCssValue("font-size");
    String itemCampaignPriceFontSize = firstItem
        .findElement(By.xpath(".//strong[@class='campaign-price']"))
        .getCssValue("font-size");
    Assert.assertTrue(itemCampaignPriceFontSize.compareTo(itemRegularPriceFontSize) > 0,
        "campaign price size is not bigger than regular " + itemCampaignPriceFontSize + " and "
            + itemRegularPriceFontSize);

    firstItem.click();
    String itemPageCampaignPriceSize = driver
        .findElement(By.xpath("//strong[@class='campaign-price']"))
        .getCssValue("font-size");
    String itemPageRegularPriceSize = driver.findElement(By.xpath("//s[@class='regular-price']"))
        .getCssValue("font-size");
    Assert.assertTrue(itemPageCampaignPriceSize.compareTo(itemPageRegularPriceSize) > 0,
        "on item page campaign price size is not bigger than regular " + itemPageCampaignPriceSize
            + " and " + itemPageRegularPriceSize);
  }


}


