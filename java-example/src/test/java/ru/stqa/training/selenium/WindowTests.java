package ru.stqa.training.selenium;

import java.util.List;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WindowTests {

  private WebDriver driver;
  private WebDriverWait wait;

  public ExpectedCondition<String> anyWindowOtherThan(Set<String> existingWindows) {
    return new ExpectedCondition<String>() {
      @Override
      @NullableDecl
      public String apply(@NullableDecl WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();
        handles.removeAll(existingWindows);
        if (!handles.isEmpty()) {
          return handles.iterator().next();
        }
        return null;
      }
    };
  }


  @BeforeTest
  public void setUp() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 3);
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
  public void testNewTabLinks() {
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    driver.findElement(By.xpath("//a[text()='Albania']")).click();
    List<WebElement> externalLinkElements = driver.findElements(
        By.xpath("//i[@class='fa fa-external-link']"));
    String mainWindow = driver.getWindowHandle();
    Set<String> existingWindows = driver.getWindowHandles();
    for (WebElement externalLinkElement : externalLinkElements) {
      externalLinkElement.click();
      String newWindow = wait.until(anyWindowOtherThan(existingWindows));
      driver.switchTo().window(newWindow);
      driver.close();
      driver.switchTo().window(mainWindow);
    }
  }
}


