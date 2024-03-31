package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartPage extends BasePage {
    @FindBy(css = "div.cart-item-name")
    private WebElement cartItemName;

    @FindBy(css = "div.cart-item-price")
    private WebElement cartItemPrice;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartItemName() {
        return cartItemName.getText();
    }

    public String getCartItemPrice() {
        return cartItemPrice.getText();
    }

    public Map<String, String> readProductInfoFromFile(String filePath) throws IOException {
        Map<String, String> productInfo = new HashMap<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            if (line.startsWith("Ürün Adı:")) {
                productInfo.put("name", line.replace("Ürün Adı:", "").trim());
            } else if (line.startsWith("Ürün Fiyatı:")) {
                productInfo.put("price", line.replace("Ürün Fiyatı:", "").trim());
            }
        }
        return productInfo;
    }

    @FindBy(css = "input.product-quantity")
    private WebElement quantityInput;

    public int getProductQuantity() {
        return Integer.parseInt(quantityInput.getAttribute("value"));
    }

    @FindBy(css = "div.empty-cart-message")
    private WebElement emptyCartMessage;

    public boolean isCartEmpty() {
        try {
            return emptyCartMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}

