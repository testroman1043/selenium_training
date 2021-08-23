package ru.stqa.training.selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Factory;

public class AllTestsFactory {

  //  @Factory
  public Object[] itemTestsKit() {
    return new Object[]{
        new ItemTests(new FirefoxDriver()),
        new ItemTests(new ChromeDriver()),
        new ItemTests(new SafariDriver()),
    };
  }

  @Factory
  public Object[] newUserTestsKit() {
    return new Object[]{
        new NewUserTests(new FirefoxDriver()),
        new NewUserTests(new ChromeDriver()),
        new NewUserTests(new SafariDriver()),
    };
  }


}


