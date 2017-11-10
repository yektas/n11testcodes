package com.n11.bau;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    WebDriver driver;

    @Before
    public void startUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/sercanyektas/Desktop/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://n11.com");
    }

    @After
    public void closeDriver() {
        driver.close();
    }

}
