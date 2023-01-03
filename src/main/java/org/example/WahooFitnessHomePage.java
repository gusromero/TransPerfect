package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WahooFitnessHomePage {
    private WebDriver driver;

    @FindBy(how= How.XPATH, using="(//a[text()='All Products'])[1]") private WebElement allProductsLink;

    public WahooFitnessHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnAllProducts(){
        allProductsLink.click();
    }
}
