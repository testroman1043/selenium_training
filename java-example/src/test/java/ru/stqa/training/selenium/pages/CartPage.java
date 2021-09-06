package ru.stqa.training.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends Page {


  public CartPage(WebDriver driver) {

    super(driver);
    PageFactory.initElements(driver, this);

  }

  public void open() {
    driver.findElement(By.xpath("//a[text()='Checkout »']")).click();
  }

  @FindBy(xpath = "//td[@class='item']/parent::tr")
  public List<WebElement> currentTableItems; // список элементов в таблице внизу

  @FindBy(xpath = "//li[@class='shortcut']")
  public List<WebElement> itemsListInCarousel; // список элементов в карусели

  @FindBy(xpath = removeButtonLocator)
  public WebElement removeButton;  // кнопка Remove


  public WebElement firstTableItem() {
    return driver.findElement(
        By.xpath("//td[@class='item']/parent::tr")); // первый товар в таблице через функцию
  }


  public void removeItem() {
    wait.until(presenceOfElementLocated(By.xpath(removeButtonLocator)));
    WebElement firstItem = firstTableItem();
    removeButton.click();
    wait.until(stalenessOf(firstItem));
  }

  public void stopTheCarousel() {
    if (isCarouselExists()) {
      itemsListInCarousel.get(0).click();
    }
  }


  public final String removeButtonLocator = "//button[text()='Remove']";

  public boolean isCarouselExists() {
    return !itemsListInCarousel.isEmpty();
  }

}
