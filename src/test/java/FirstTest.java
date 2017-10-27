import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

public class FirstTest {

    WebDriver driver;
    String keyword = "samsung";

    @Before
    public void startUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/sercanyektas/Desktop/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.n11.com");
    }

    @Test
    public void shouldLogin() {

        WebElement signInButton = driver.findElement(By.className("btnSignIn"));
        signInButton.click();

        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        email.sendKeys("bau@bau.com");
        password.sendKeys("123qwe");

        loginButton.click();

        String userName = driver.findElement(By.className("username")).getText();
        assertThat("When a buyer logged in", userName, equalTo("Sercan Yektaş"));

        // Search an item
        WebElement searchBar = driver.findElement(By.id("searchData"));
        searchBar.sendKeys(keyword);
        WebElement searchButton = driver.findElement(By.className("iconSearch"));
        searchButton.click();

        // Check if the search is correct
        String resultText = driver.findElement(By.cssSelector(".resultText > h1")).getText();

        assertTrue(resultText.equalsIgnoreCase(keyword));


        //XPath selector

        // Use @ before selector
        // Use () after text method
        driver.findElement(By.xpath("//input[@id='searchData']")); //With id

        driver.findElement(By.xpath("//input[@placeholder='Milyonlarca ürün arasında arama yapın']")); // With placeholder

        driver.findElement(By.xpath("//a[text()='Süper Fırsatlar']")); //With text

        driver.findElement(By.xpath("//div[@class='panelContent']//*[text(), 'Ürün Özellikleri']")); // With text

        driver.findElement(By.xpath("//div[@class='panelContent']//*[contains(text(), 'Özellikleri')]")); // Using contains method

        driver.findElement(By.xpath("//*[contains(@id, 'buyerSearchDatatable')]"));

        driver.findElement(By.xpath("//*[contains(text(), 'buyerSearchDatatable')]"));

        driver.findElement(By.xpath("//*[text(), 'buyerSearchDatatable']"));

        //XPath selector

        driver.findElement(By.id("tabPanelProDetail")).getAttribute("class");
        driver.findElement(By.id("tabPanelProDetail")).getTagName();

    }

    @Test
    public void checkResults() {


        // Search an item
        WebElement searchBar = driver.findElement(By.id("searchData"));
        searchBar.sendKeys(keyword);
        WebElement searchButton = driver.findElement(By.className("iconSearch"));
        searchButton.click();


        // Check if the results are valid

        // Method 1
        ArrayList<WebElement> list = new ArrayList<WebElement>();
        WebElement mainDiv = driver.findElement(By.className("resultListGroup"));
        list.addAll(mainDiv.findElements(By.className("columnContent")));
        for (int i = 0; i < list.size(); i++) {
            String word = list.get(i).findElement(By.className("productName")).getText();
            assertTrue(word.toLowerCase().contains(keyword));
        }
        // Method 2
        List<WebElement> productList = driver.findElements(By.cssSelector("#view .productName"));
        List<WebElement> productList1 = driver.findElements(By.xpath("//*[@id='view']//*[contains(@class, 'productName')]"));
        for (WebElement product : productList) {
            String productTitle = product.getText().toLowerCase();
            System.out.println("Asserting: " + productTitle);
            assertTrue(product.getText().toLowerCase().contains(keyword));
        }
    }

    @After
    public void closeDriver() {
        driver.close();
    }

}
