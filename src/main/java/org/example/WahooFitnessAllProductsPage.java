package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class WahooFitnessAllProductsPage {
    private WebDriver driver;

    @FindBy(how=How.XPATH, using="//ul[@class='products-grid']//li/div[2]/p/a")
    private List<WebElement> allProductsList;

    public WahooFitnessAllProductsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement selectRandomProduct(){
        int randomInt = new Random().nextInt(allProductsList.size()-1);
        WebElement selectedProduct = allProductsList.get(randomInt);
        System.out.println("Selected: " + selectedProduct.getText() );
        return selectedProduct;
    }

}
