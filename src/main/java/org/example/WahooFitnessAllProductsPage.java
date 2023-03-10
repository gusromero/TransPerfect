package org.example;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class WahooFitnessAllProductsPage extends WahooFitnessPage {

    @FindBy(how = How.XPATH, using = "//ul[@class='products-grid']//li/div[2]/p/a")
    private List<WebElement> allProductsList;

    @FindBy(how = How.XPATH, using = "//p[text()='This product is out of stock']")
    private WebElement outOfStockText;

    @FindBy(how = How.XPATH, using = "//p[text()='Coming soon']")
    private WebElement comingSoonText;

    @FindBy(how = How.XPATH, using = "//select[@class='super-attribute-select']")
    private WebElement selectColorSizeDropDown;

    @FindBy(how = How.XPATH, using = "//div[@class='add-to-cart']//button[@title='Add to Cart']")
    private WebElement addToCart;

    @FindBy(how = How.XPATH, using = "//div[@data-block='minicart']")
    private WebElement miniCartBlock;

    @FindBy(how = How.XPATH, using = "//a[@id='btn-minicart-close']")
    private WebElement closeMiniCart;

    @FindBy(how = How.XPATH, using = "(//a[@title='Remove item'])[1]")  // 1 or 2
    private WebElement removeItem;

    @FindBy(how = How.XPATH, using = "//div[@class='modal-content']//div[contains(text(),'remove this item')]")
    private WebElement removeItemPopUp;

    @FindBy(how = How.XPATH, using = "//button[@class='action-primary action-accept']")
    private WebElement OKButton;

    @FindBy(how = How.XPATH, using = "//button[@id='top-cart-btn-checkout']")
    private WebElement checkOutButton;

    public WahooFitnessAllProductsPage(WebDriver driver) {
        super(driver);
    }

    public void selectRandomProduct() throws InterruptedException {
        int randomInt = new Random().nextInt(allProductsList.size() - 1);
        WebElement selectedProduct = allProductsList.get(randomInt);
        System.out.println("Selected: " + selectedProduct.getText());
        selectedProduct.click();
        Thread.sleep(timeout);
    }

    public void addToCart() throws InterruptedException {
        if (!isAvailable()) {
            backToAllProductsPage();
            selectRandomProduct();
        }
        selectColorSize();
        addToCart.click();
        Thread.sleep(timeout);
    }

    public boolean isMiniCartShown() throws InterruptedException {
        boolean miniCartShown = false;
        Thread.sleep(timeout);
        String displayAttribute = miniCartBlock.getAttribute("class");
        if (displayAttribute.contains("active"))
            miniCartShown = true;
        return miniCartShown;
    }

    public void closeMiniCart() throws InterruptedException {
        closeMiniCart.click();
        Thread.sleep(timeout);
    }

    public void backToAllProductsPage() throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(timeout);
    }

    public boolean isAvailable() {
        try {
            if (outOfStockText.isDisplayed() || comingSoonText.isDisplayed()) {
                return false;
            }
        } catch (NoSuchElementException exp) {
            return true;
        }
        return false;
    }

    public void selectColorSize() {
        try {
            if (selectColorSizeDropDown.isDisplayed()) {
                Select selColorSize = new Select(selectColorSizeDropDown);
                selColorSize.selectByIndex(1);
                System.out.println("Color or Size selected");
                Thread.sleep(timeout);
            }
        } catch (NoSuchElementException | InterruptedException ex) {
            System.out.println("Nothing to select");
        }
    }

    public void removeProduct() throws InterruptedException {
        removeItem.click();
        if (removeItemPopUp.isDisplayed()) {
            Thread.sleep(timeout);
            OKButton.click();
        }
    }

    public void checkOut() throws InterruptedException {
        checkOutButton.click();
        Thread.sleep(timeout);
    }
}
