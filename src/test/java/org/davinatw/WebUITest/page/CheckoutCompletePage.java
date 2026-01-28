package org.davinatw.WebUITest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {
    WebDriver driver;

    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
    }

    public boolean validateCheckoutCompletePage(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // This waits until the URL actually contains /cart.html
            System.out.println("Waiting for checkoutCompletePage URL... Current is:" + driver.getCurrentUrl());
            return wait.until(ExpectedConditions.urlContains("/checkout-complete.html"));
        } catch (Exception e) {
            // If it times out, print the actual URL to help you debug
            System.out.println("Failed to reach checkoutCompletePage. Final URL was: " + driver.getCurrentUrl());
            return false;
        }

    }


}
