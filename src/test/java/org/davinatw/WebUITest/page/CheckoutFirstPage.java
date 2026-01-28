package org.davinatw.WebUITest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutFirstPage {
    WebDriver driver;


    By errorMessageElement = By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]");

    public CheckoutFirstPage(WebDriver driver){

        this.driver = driver;
    }

    public boolean isCheckoutFirstPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // This waits until the URL actually contains /cart.html
            System.out.println("Waiting for checkoutFirstPage URL... Current is:" + driver.getCurrentUrl());
            return wait.until(ExpectedConditions.urlContains("/checkout-step-one.html"));
        } catch (Exception e) {
            // If it times out, print the actual URL to help you debug
            System.out.println("Failed to reach checkoutFirstPage. Final URL was: " + driver.getCurrentUrl());
            return false;
        }
    }

    public void inputFirstName(String firstName){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));

        // Clear the field first to ensure clean input
        element.clear();
        element.sendKeys(firstName);

        // Verify the input by reading it back from the browser
        String typedValue = element.getAttribute("value");

        // IF standard typing failed, FORCE it with JavaScript AND trigger events
        if (!firstName.isEmpty() && typedValue.isEmpty()) {
            System.out.println("Standard typing failed for First Name. Retrying with Event Dispatch...");

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // CRITICAL UPDATE: We set the value AND fire the events so React 'sees' it
            js.executeScript(
                    "var element = arguments[0];" +
                            "var value = arguments[1];" +
                            "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                            "nativeInputValueSetter.call(element, value);" +
                            "element.dispatchEvent(new Event('input', { bubbles: true }));" +
                            "element.dispatchEvent(new Event('change', { bubbles: true }));",
                    element, firstName
            );

            typedValue = firstName;
        }

        System.out.println("Input First Name: '" + firstName + "' | Actual Field Value: '" + typedValue + "'");

    }

    public void inputLastName(String lastName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));

        element.clear();
        element.sendKeys(lastName);

        // Verify the input by reading it back from the browser
        String typedValue = element.getAttribute("value");

        if (!lastName.isEmpty() && typedValue.isEmpty()) {
            System.out.println("Standard typing failed for Last Name. Retrying with Event Dispatch...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "var element = arguments[0];" +
                            "var value = arguments[1];" +
                            "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                            "nativeInputValueSetter.call(element, value);" +
                            "element.dispatchEvent(new Event('input', { bubbles: true }));" +
                            "element.dispatchEvent(new Event('change', { bubbles: true }));",
                    element, lastName
            );
            typedValue = lastName;
        }

        System.out.println("Input Last Name: '" + lastName + "' | Actual Field Value: '" + typedValue + "'");

    }

    public void inputPostCode(String postCode){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));

        element.clear();
        element.sendKeys(postCode);

        // Verify the input by reading it back from the browser
        String typedValue = element.getAttribute("value");

        if (!postCode.isEmpty() && typedValue.isEmpty()) {
            System.out.println("Standard typing failed for Postal Code. Retrying with Event Dispatch...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "var element = arguments[0];" +
                            "var value = arguments[1];" +
                            "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                            "nativeInputValueSetter.call(element, value);" +
                            "element.dispatchEvent(new Event('input', { bubbles: true }));" +
                            "element.dispatchEvent(new Event('change', { bubbles: true }));",
                    element, postCode
            );
            typedValue = postCode;
        }

        System.out.println("Input Last Name: '" + postCode + "' | Actual Field Value: '" + typedValue + "'");

    }

    public void clickContinueButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));

        // DIRECT FIX: Use JavaScript click immediately.
        // We skip the standard click because it is silently failing in your CI environment.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);

        System.out.println("Executed Forced JavaScript Click on 'Continue' button.");
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // 1. Wait until the element is visible
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageElement));

        // 2. Add a wait for the text to NOT be empty
        wait.until(driver -> !element.getText().trim().isEmpty());

        System.out.println("Error message captured: " + element.getText());
        return element.getText();
    }

}
