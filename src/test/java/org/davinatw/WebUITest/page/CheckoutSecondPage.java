package org.davinatw.WebUITest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutSecondPage {
    WebDriver driver;
    By totalItemPrice = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]");
    By taxPrice = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]");
    By totalFinalPrice = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]");

    By finishButton = By.id("finish");

    public CheckoutSecondPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isOnCheckoutSecondPage(){
        return driver.getCurrentUrl().contains("/checkout-step-two.html");
    }

    public String textTotalPrice(){
        return driver.findElement(totalItemPrice).getText();
    }

    public String textTaxPrice(){
        return driver.findElement(taxPrice).getText();
    }

    public String textFinalPrice(){
        return driver.findElement(totalFinalPrice).getText();
    }

    public void clickFinishButton(){
        driver.findElement(finishButton).click();
    }

}
