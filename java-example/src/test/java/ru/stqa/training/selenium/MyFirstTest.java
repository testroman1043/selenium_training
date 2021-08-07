package ru.stqa.training.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyFirstTest {
    private WebDriver driver;


    @BeforeTest
    public void start(){
        driver = new ChromeDriver();
    }

    @Test
    public void openBrowser(){
        driver.get("https://www.rambler.ru");

    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }


}
