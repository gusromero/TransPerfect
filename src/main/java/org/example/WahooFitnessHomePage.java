package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class WahooFitnessHomePage extends WahooFitnessPage {
    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "(//a[text()='All Products'])[1]")
    private WebElement allProducts;

    WahooFitnessHomePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAllProducts() {
        allProducts.click();
    }
}
