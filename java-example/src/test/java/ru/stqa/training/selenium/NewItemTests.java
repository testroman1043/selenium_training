package ru.stqa.training.selenium;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewItemTests {

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
  public void testAddNewProduct() {
    //General
    driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
    driver.findElement(By.xpath("//a[text()=' Add New Product']")).click();
    driver.findElement(By.xpath("//label[text()=' Enabled']")).click();
    driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys("New Item");
    driver.findElement(By.xpath("//input[@name='code']")).sendKeys("code");
    driver.findElement(By.xpath("//input[@data-name='Subcategory' and @type='checkbox']")).click();
    driver.findElement(By.xpath("//select[@name='default_category_id']")).click();
    driver.findElement(By.xpath("//option[text()='Subcategory']")).click();
    driver.findElement(By.xpath("//input[@name='product_groups[]' and @value='1-1']")).click();
    driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(Keys.HOME + "3");
    driver.findElement(By.xpath("//input[@name='date_valid_from']"))
        .sendKeys(Keys.HOME + "27.08.2021");
    driver.findElement(By.xpath("//input[@name='date_valid_to']"))
        .sendKeys(Keys.HOME + "27.08.2022");
    File image = new File("src/test/resources/img/37m.jpeg");
    String absolutePath = image.getAbsolutePath();
    driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(absolutePath);
    //Information
    driver.findElement(By.xpath("//a[text()='Information']")).click();
    driver.findElement(By.xpath("//select[@name='manufacturer_id']")).click();
    driver.findElement(By.xpath("//option[text()='ACME Corp.']")).click();
    driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("New, item");
    driver.findElement(By.xpath("//input[@name='short_description[en]']"))
        .sendKeys("short description");
    driver.findElement(By.xpath("//div[@class='trumbowyg-editor']"))
        .sendKeys("description description");
    driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("Head title");
    driver.findElement(By.xpath("//input[@name='meta_description[en]']"))
        .sendKeys("meta description");
    //prices
    driver.findElement(By.xpath("//a[text()='Prices']")).click();
    driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys(Keys.HOME + "4");
    driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']")).click();
    driver.findElement(By.xpath("//option[text()='Euros']")).click();
    driver.findElement(By.xpath("//input[@name='prices[USD]']")).sendKeys(Keys.HOME + "10");
    driver.findElement(By.xpath("//input[@name='prices[EUR]']")).sendKeys(Keys.HOME + "15");
    driver.findElement(By.xpath("//button[@name='save']")).click();
    Assert.assertFalse(driver.findElements(By.xpath("//td/a[text()='New Item']")).isEmpty(),
        "New Item not found");


  }

}
