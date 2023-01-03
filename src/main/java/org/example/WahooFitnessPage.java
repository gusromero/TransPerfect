package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class WahooFitnessPage {

    protected WebDriver driver;

    protected final int timeout = 3000;

    WahooFitnessPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
