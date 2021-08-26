package ru.stqa.training.selenium;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CartTests {

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeTest
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 3);
  }

  @AfterTest
  public void stop() {
    driver.quit();
  }

  @Test
  public void testCart() {
    for (int i = 1; i < 4; i++) {
      driver.get("http://localhost/litecart/en/");
      List<WebElement> itemsList = driver.findElements(
          By.xpath("//div[@id='box-most-popular']//li[contains(@class,'product')]"));
      itemsList.get(0).click();
      WebElement cartQty = driver.findElement(By.xpath("//span[@class='quantity']"));
      if (Objects.equals(driver.findElement(By.xpath("//h1")).getText(), "Yellow Duck")) {
        driver.findElement(By.xpath("//select[@name='options[Size]']")).click();
        driver.findElement(By.xpath("//option[text()='Small']")).click();
      }
      driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
      wait.until(textToBePresentInElement(cartQty, String.valueOf(i)));
    }
    driver.findElement(By.xpath("//a[text()='Checkout »']")).click();
    int tableSize = driver.findElements(By.xpath("//td[@class='item']/parent::tr")).size();
    for (int i = 0; i < tableSize; i++) {
      List<WebElement> currentTableItem = driver.findElements(
          By.xpath("//td[@class='item']/parent::tr"));
      List<WebElement> itemList = driver.findElements(By.xpath("//li[@class='shortcut']"));
      if (!itemList.isEmpty()) {
        itemList.get(0).click();
      }
      String removeButtonLocator = "//button[text()='Remove']";
      wait.until(presenceOfElementLocated(By.xpath(removeButtonLocator)));
      driver.findElement(By.xpath(removeButtonLocator)).click();
      wait.until(stalenessOf(currentTableItem.get(0)));
    }
  }
}

