package org.example;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class WahooFitnessCheckOutPage extends WahooFitnessPage {

    @FindBy(how=How.XPATH, using="//td[contains(text(),'Express Shipping')]")
    private WebElement expressShipping;
    @FindBy(how=How.XPATH, using="//input[@name='qty']")
    private WebElement quantity;
    @FindBy(how=How.XPATH, using="//button[@title='Update']")
    private WebElement updateButton;
    @FindBy(how=How.XPATH, using="//fieldset//input[@id='customer-email']")
    private WebElement customerEmail;
    @FindBy(how=How.XPATH, using="//fieldset//input[@name='firstname']")
    private WebElement firstName;
    @FindBy(how=How.XPATH, using="//fieldset//input[@name='lastname']")
    private WebElement lastName;
    @FindBy(how=How.XPATH, using="(//input[@name='street[0]'])[1]")
    private WebElement streetAddress;
    @FindBy(how=How.XPATH, using="(//input[@name='city'])[1]")
    private WebElement city;
    @FindBy(how=How.XPATH, using="(//select[@name='country_id'])[1]")
    private WebElement country;
    @FindBy(how=How.XPATH, using="(//select[@name='region_id'])[1]")
    private WebElement state;
    @FindBy(how=How.XPATH, using="(//input[@name='postcode'])[1]")
    private WebElement zipCode;
    @FindBy(how=How.XPATH, using="(//input[@name='telephone'])[1]")
    private WebElement phoneNumber;
    @FindBy(how=How.XPATH, using="//input[@name='cardnumber']")
    private WebElement creditCard;
    @FindBy(how=How.XPATH, using="//button[@title=' Place Order']")
    private WebElement placeOrderButton;
    @FindBy(how=How.XPATH, using="//div[contains(text(),'Your card was declined')]")
    private WebElement cardDeclined;

    @FindBy(how=How.XPATH, using="//div[@id='customer-email-error']")
    private WebElement errorMessage;


    public WahooFitnessCheckOutPage(WebDriver driver){
        super(driver);
    }

    public void selectExpressShipping() throws InterruptedException{
        expressShipping.click();
        Thread.sleep(timeout);
    }

    public void updateProductQty() throws InterruptedException{
        quantity.sendKeys("2");
        quantity.sendKeys(Keys.TAB);
        updateButton.click();
        Thread.sleep(timeout);
    }

    public void enterCustomerDetails() throws InterruptedException{
        customerEmail.sendKeys("someone@whocares.com");
        firstName.sendKeys("Test");
        lastName.sendKeys("Tester");
        streetAddress.sendKeys("Comandante Izarduy 67");
        city.sendKeys("Barcelona");
        selectFromDropDown(country, "Spain");
        selectFromDropDown(state, "Barcelona");
        zipCode.sendKeys("08940");
        phoneNumber.sendKeys("5555555555");
        phoneNumber.sendKeys(Keys.TAB);
        Thread.sleep(timeout);
    }

    public void selectFromDropDown(WebElement dropDownEle, String value){
        Select selectDropDwn = new Select(dropDownEle);
        selectDropDwn.selectByValue(value);
    }

    public void enterPaymentDetails(){
        creditCard.sendKeys("4111111111111111");
        creditCard.sendKeys(Keys.TAB);
        creditCard.sendKeys("0824111");
        creditCard.sendKeys(Keys.TAB);
    }

    public void placeOrder(){
        placeOrderButton.click();
    }

    public boolean isCardDeclined(){
        try{
            if(cardDeclined.isDisplayed())
                System.out.println("Card declined");
                return true;

        }catch(NoSuchElementException exp){
            System.out.println("The card is valid");
            return false;
        }
    }

    public boolean isErrorMessageShown() {
        try{
            if(errorMessage.isDisplayed())
                System.out.println("Error message shown");
                return true;

        }catch(NoSuchElementException exp){
            System.out.println("No error message");
            return false;
        }
    }
}
