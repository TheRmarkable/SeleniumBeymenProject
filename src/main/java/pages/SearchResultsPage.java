package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Random;

public class SearchResultsPage extends BasePage {
    @FindBy(xpath = "//*[@id='productList']//div[contains(@class,'col-sm-4 col-md-4 col-lg-4 col-xl-4 col-xxl-3 o-productList__itemWrapper')]//div[contains(@class,'o-productList__item')]//div[contains(@class, 'm-productCard')]")
    private List<WebElement> products;


    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage selectRandomProduct() {
        int index = new Random().nextInt(products.size());
        products.get(index).click();
        return new ProductPage(driver);
    }
}

