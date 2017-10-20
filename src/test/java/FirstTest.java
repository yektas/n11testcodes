import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

public class FirstTest {

    @Test
    public void shouldLogin(){
        System.setProperty("webdriver.chrome.driver", "/home/sercan/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.n11.com");
        WebElement signInButton = driver.findElement(By.className("btnSignIn"));
        signInButton.click();

        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        email.sendKeys("emailaddress");
        password.sendKeys("password");

        loginButton.click();

        String userName = driver.findElement(By.className("username")).getText();
        assertThat("When a buyer logged in", userName, equalTo("Sercan Yektaş"));

        // Search an item
        WebElement searchBar = driver.findElement(By.id("searchData"));
        String searchText = "macbook";
        searchBar.sendKeys(searchText);
        WebElement searchButton = driver.findElement(By.className("iconSearch"));
        searchButton.click();

        // Check if the search is correct
        String resultText = driver.findElement(By.cssSelector(".resultText > h1")).getText();
        System.out.println(resultText);
        assertTrue(resultText.equalsIgnoreCase("Macbook"));

        driver.close();
    }
}
