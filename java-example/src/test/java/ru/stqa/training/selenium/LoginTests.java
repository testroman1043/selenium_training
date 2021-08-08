package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class LoginTests {
    private WebDriver chromeDriver;
    private WebDriver firefoxDriver;
    private WebDriver safariDriver;
    private WebDriver nightDriver;



    @BeforeTest
    public void start(){
        chromeDriver = new ChromeDriver();
        firefoxDriver = new FirefoxDriver();
        safariDriver = new SafariDriver();
        String binary = "/Applications/Firefox Nightly.app/Contents/MacOS/firefox-bin";
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(binary);
        nightDriver = new FirefoxDriver(firefoxOptions);
    }

    public void testLogin(WebDriver driver){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }


    @Test
    public void testChrome(){
        testLogin(chromeDriver);
    }

    @Test
    public void testFirefox(){
        testLogin(firefoxDriver);
    }

    @Test
    public void testSafari(){
        testLogin(safariDriver);
    }

    @Test
    public void testNight(){
        testLogin(nightDriver);
    }


    @AfterTest
    public void stop(){
        chromeDriver.quit();
        firefoxDriver.quit();
        safariDriver.quit();
        nightDriver.quit();
    }
}
