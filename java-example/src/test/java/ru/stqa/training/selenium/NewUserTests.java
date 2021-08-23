package ru.stqa.training.selenium;

import java.util.concurrent.ThreadLocalRandom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewUserTests {

  private final WebDriver driver;

  public NewUserTests(WebDriver driver) {
    this.driver = driver;
  }


  @BeforeClass
  public void setUp() {
  }

  @AfterClass
  public void stop() {
    driver.quit();
  }


  @Test
  public void testNewUser() {
    String password = "12345678";
    driver.get("http://localhost/litecart/en/create_account");
    int randomNum = ThreadLocalRandom.current().nextInt();
    String eMail = "User" + randomNum + "@test.com";
    driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("FirstName");
    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("LastName");
    driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("address1");
    driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("12345");
    driver.findElement(By.xpath("//input[@name='city']")).sendKeys("city17");
    driver.findElement(By.xpath("//input[@name='email']")).sendKeys(eMail);
    driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("+79161234567");
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);
    driver.findElement(By.xpath("//td[contains(text(),'Country')]//span[@role='combobox']"))
        .click();
    driver.findElement(By.xpath("//li[text()='United States']")).click();
    driver.findElement(By.xpath("//select[@name='zone_code']")).click();
    driver.findElement(By.xpath("//option[text()='Arkansas']")).click();
    driver.findElement(By.xpath("//button[@name='create_account']")).click();
    driver.findElement(By.xpath("//div[@class='content']//a[text()='Logout']")).click();
    driver.findElement(By.xpath("//input[@name='email']")).sendKeys(eMail);
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    driver.findElement(By.xpath("//div[@class='content']//button[text()='Login']")).click();
    driver.findElement(By.xpath("//div[@class='content']//a[text()='Logout']")).click();


  }


}
