package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(css = ".o-header__search--input")
    private WebElement searchBox;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        driver.get("https://www.beymen.com");
        waitForPageToLoad();
    }

    public void enterSearchTerm(String searchTerm) {
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
    }

    public void clearSearch() {
        searchBox.clear();
    }

    public SearchResultsPage submitSearch() {
        searchBox.submit();
        return new SearchResultsPage(driver);
    }
}



