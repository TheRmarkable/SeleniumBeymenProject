package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProductPage extends BasePage {
    @FindBy(css = "h1.product-name")
    private WebElement productName;

    @FindBy(css = "span.price")
    private WebElement productPrice;

    @FindBy(css = "button.add-to-basket")
    private WebElement addToCartButton;

    @FindBy(css = "input.quantity-input")
    private WebElement quantityInput;

    @FindBy(css = "button.increase-quantity")
    private WebElement increaseQuantityButton;

    @FindBy(css = "span.cart-price")
    private WebElement cartPrice;

    @FindBy(css = "button.remove-from-cart")
    private WebElement removeFromCartButton;

    @FindBy(css = "button.cart-icon")
    private WebElement cartIcon;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public void increaseProductQuantity() {
        increaseQuantityButton.click();
    }

    public String getCartPrice() {
        return cartPrice.getText();
    }

    public void removeFromCart() {
        removeFromCartButton.click();
    }

    public void writeProductInfoToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Ürün Adı: " + getProductName());
            writer.newLine();
            writer.write("Ürün Fiyatı: " + getProductPrice());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CartPage goToCartPage() {
        cartIcon.click();
        return new CartPage(driver);
    }

}

