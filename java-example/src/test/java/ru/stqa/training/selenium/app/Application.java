package ru.stqa.training.selenium.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.pages.AllItemsPage;
import ru.stqa.training.selenium.pages.CartPage;
import ru.stqa.training.selenium.pages.ItemPage;

public class Application {

  private WebDriver driver;
  private WebDriverWait wait;
  public int i;

  private final AllItemsPage allItemsPagePage;
  private final ItemPage itemPage;
  private final CartPage cartPage;

  public Application() {
    driver = new ChromeDriver();
    allItemsPagePage = new AllItemsPage(driver);
    itemPage = new ItemPage(driver);
    cartPage = new CartPage(driver);
  }

  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 5);
  }

  public void stop() {
    driver.quit();
  }


  public void clickFirstItemOnMainPage() {
    allItemsPagePage.open();
    allItemsPagePage.popularItems.get(0).click();
  }

  public void addItemToTheCart(int n) {
    itemPage.addToCart(n);
  }

  public void clearCart() {
    cartPage.open();
    int tableSize = cartPage.currentTableItems.size();
    for (int i = 0; i < tableSize; i++) {
      cartPage.stopTheCarousel();
      cartPage.removeItem();
    }
  }


}

