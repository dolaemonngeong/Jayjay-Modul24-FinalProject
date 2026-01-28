package org.davinatw.WebUITest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutSecondPage {
    WebDriver driver;

    By totalFinalPrice = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]");
    By finishButton = By.id("finish");

    public CheckoutSecondPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isOnCheckoutSecondPage(){
        System.out.println("Checking if checkout second page is displayed");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // This waits for the URL to change before returning true to your assertTrue
            System.out.println("Waiting for checkoutSecondPage URL... Current is: " + driver.getCurrentUrl());
            return wait.until(ExpectedConditions.urlContains("/checkout-step-two.html"));
        } catch (Exception e) {
            System.out.println("Failed to reach checkoutSecondPage. Final URL was: " + driver.getCurrentUrl());
            return false;
        }
    }


    public String textFinalPrice(){
        return driver.findElement(totalFinalPrice).getText();
    }

    public void clickFinishButton(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(finishButton));

        // DIRECT FIX: Use JavaScript click immediately.
        // We skip the standard click because it is silently failing in your CI environment.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);

        System.out.println("Executed Forced JavaScript Click on 'Finish' button.");

    }

}
