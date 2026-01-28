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

    By firstNameInput = By.xpath("//*[@id=\"first-name\"]");
    By lastNameInput = By.xpath("//*[@id=\"last-name\"]");
    By postCodeInput = By.xpath("//*[@id=\"postal-code\"]");
//    By continueButton = By.xpath("//*[@id=\"continue\"]");
    By continueButton = By.id("continue");

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
//        driver.findElement(firstNameInput).sendKeys(firstName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));

        // Clear the field first to ensure clean input
        element.clear();
        element.sendKeys(firstName);

        // Verify the input by reading it back from the browser
        String typedValue = element.getAttribute("value");
        System.out.println("Input First Name: '" + firstName + "' | Actual Field Value: '" + typedValue + "'");

//        try {
//            // Attempt standard interaction
//            element.click();
//            element.clear();
//            element.sendKeys(firstName);
//        } catch (Exception e) {
//            System.out.println("Error entering First Name. Retrying with JavaScript...");
//            // Fallback: Use JavaScript to force the value if standard typing fails
//            WebElement element = driver.findElement(firstNameInput);
//            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + firstName + "';", element);
//        }
    }

    public void inputLastName(String lastName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));

        element.click();
        element.clear();
        element.sendKeys(lastName);
//        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void inputPostCode(String postCode){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));

        element.click();
        element.clear();
        element.sendKeys(postCode);
//        driver.findElement(postCodeInput).sendKeys(postCode);
    }

    public void clickContinueButton(){
//        driver.findElement(continueButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(continueButton));

        try {
            // Attempt standard click
            element.click();

            // IMMEDIATE CHECK: Did something happen?
            // We check if the error message appears OR if the URL changes.
            // If neither happens within 1 second, we assume the click failed.
            boolean actionTriggered = wait.until(driver ->
                    driver.getCurrentUrl().contains("checkout-step-two") ||
                            driver.findElement(errorMessageElement).isDisplayed() // Ensure you have access to errorMessageElement here
            );

        } catch (Exception e) {
            System.out.println("Standard click ignored. Forcing JavaScript click.");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    public String getErrorMessage() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageElement));
//        System.out.println("Waiting the error message display:" + element.getText());
//        return element.getText();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // 1. Wait until the element is visible
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageElement));

        // 2. Add a wait for the text to NOT be empty
        wait.until(driver -> !element.getText().trim().isEmpty());

        System.out.println("Error message captured: " + element.getText());
        return element.getText();
    }

}
