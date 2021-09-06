package ru.stqa.training.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends Page {

  public ItemPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {
    driver.get("http://localhost/litecart/en/");
    driver.findElements(
        By.xpath("//div[@id='box-most-popular']//li[contains(@class,'product')]")).get(0).click();
  }

  @FindBy(xpath = "//div[@id='box-most-popular']//li[contains(@class,'product')]")
  public List<WebElement> popularItems; // список most popular

  @FindBy(xpath = "//span[@class='quantity']")
  public WebElement cartQty; // счетчик товаров у корзины

  public void addToCart(int n) {
    if (Objects.equals(driver.findElement(By.xpath("//h1")).getText(), "Yellow Duck")) {
      driver.findElement(By.xpath("//select[@name='options[Size]']")).click();
      driver.findElement(By.xpath("//option[text()='Small']")).click();
    }
    driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
    wait.until(textToBePresentInElement(cartQty, String.valueOf(n)));
  }


}