package ru.stqa.training.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CartWithPOTests extends TestBase {

  private WebDriver driver;
  private WebDriverWait wait;
  private int i;

  @Test
  public void testCart() {
    for (int i = 1; i < 4; i++) {
      app.clickFirstItemOnMainPage();
      app.addItemToTheCart(i);
    }
    app.clearCart();
  }
}




