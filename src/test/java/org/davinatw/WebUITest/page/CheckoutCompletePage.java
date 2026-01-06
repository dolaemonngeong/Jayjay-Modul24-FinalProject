package org.davinatw.WebUITest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    WebDriver driver;

    By thankYouText = By.xpath("//*[@id=\"checkout_complete_container\"]/h2");
    By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
    }

    public boolean validateCheckoutCompletePage(){
        return driver.getCurrentUrl().contains("/checkout-complete.html");
    }

    public void clickBackHomeButton(){
        driver.findElement(backHomeButton).click();
    }

}
