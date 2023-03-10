package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;


class WahooFitnessTest {

    private WebDriver driver;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.get("https://eu.wahoofitness.com/");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        driver.close();
    }

    @org.junit.jupiter.api.Test
    void fullTestWahooFitness() throws InterruptedException {
        WahooFitnessHomePage homePage = new WahooFitnessHomePage(driver);
        homePage.clickOnAllProducts();

        WahooFitnessAllProductsPage allProducts = new WahooFitnessAllProductsPage(driver);
        allProducts.selectRandomProduct();
        allProducts.addToCart();
        if (allProducts.isMiniCartShown()) {
            allProducts.closeMiniCart();
        }
        allProducts.backToAllProductsPage();
        allProducts.selectRandomProduct();
        allProducts.addToCart();
        allProducts.removeProduct();
        allProducts.checkOut();

        WahooFitnessCheckOutPage checkOutPage = new WahooFitnessCheckOutPage(driver);
        checkOutPage.placeOrder();
        if (!checkOutPage.isErrorMessageShown()) {
            System.out.println("Error message should have appeared");
        }
        checkOutPage.selectExpressShipping();
        checkOutPage.updateProductQty();
        checkOutPage.enterCustomerDetails();
        checkOutPage.enterPaymentDetails();
        checkOutPage.placeOrder();

        if (!checkOutPage.isCardDeclined()) {
            System.out.println("Card should have been declined");
        }
    }
}