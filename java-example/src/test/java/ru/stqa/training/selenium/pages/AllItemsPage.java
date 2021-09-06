package ru.stqa.training.selenium.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllItemsPage extends Page {

  public AllItemsPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {
    driver.get("http://localhost/litecart/en/");
  }

  @FindBy(xpath = "//div[@id='box-most-popular']//li[contains(@class,'product')]")
  public List<WebElement> popularItems; // список most popular

}
