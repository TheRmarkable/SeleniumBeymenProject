package tests;

import helperFunctions.ExcelReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import configs.ConfigLoader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BeymenTest {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() {
        ConfigLoader configLoader = new ConfigLoader();
        String chromeDriverPath = configLoader.getChromeDriverPath();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @Test
    public void testCompleteProductFlow() throws InterruptedException, IOException {
        String filePath = "path/to/your/excel.xlsx";
        String searchTermShort = ExcelReader.readCellValue(filePath, 0, 0, 0);
        String searchTermShirt = ExcelReader.readCellValue(filePath, 0, 0, 1);

        homePage.navigateToHomePage();
        homePage.enterSearchTerm(searchTermShort);
        homePage.clearSearch();
        homePage.enterSearchTerm(searchTermShirt);
        SearchResultsPage searchResultsPage = homePage.submitSearch();
        ProductPage productPage = searchResultsPage.selectRandomProduct();
        String productPagePrice = productPage.getProductPrice();

        productPage.addToCart();

        CartPage cartPage = productPage.goToCartPage();

        String cartPrice = cartPage.getCartItemPrice();
        assertEquals(productPagePrice, cartPrice);

        ConfigLoader configLoader = new ConfigLoader();
        String txtFilePath = configLoader.getTxtFilePath();
        productPage.writeProductInfoToFile(txtFilePath);
        Map<String, String> productInfoFromFile = cartPage.readProductInfoFromFile(txtFilePath);

        String cartProductName = cartPage.getCartItemName();
        assertTrue(cartProductName.contains(productInfoFromFile.get("name")));
        assertEquals(productInfoFromFile.get("price"), cartPrice);

        productPage.increaseProductQuantity();
        Thread.sleep(1000);

        int quantity = cartPage.getProductQuantity();
        assertEquals(2, quantity);

        productPage.removeFromCart();
        Thread.sleep(1000);

        assertTrue(cartPage.isCartEmpty());
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
