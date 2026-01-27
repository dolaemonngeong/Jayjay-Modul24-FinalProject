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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the field to actually appear before typing
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        firstNameField.sendKeys(firstName);
//        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void inputLastName(String lastName){

        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void inputPostCode(String postCode){
        driver.findElement(postCodeInput).sendKeys(postCode);
    }

    public void clickContinueButton(){
//        driver.findElement(continueButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Wait for visibility
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton));
        System.out.println("Continue Button is visible, proceeding to click. + " + element.isDisplayed());

        try {
            System.out.println("try standard click");
            // 2. Try standard click
            element.click();
            // 3. Wait for the URL to actually change to cart.html
//            wait.until(ExpectedConditions.urlContains("/checkout-step-two.html"));
        } catch (Exception e) {
            System.out.println("Standard click failed to redirect, trying JavaScript click.");
            // 4. Backup: JavaScript click
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            // 5. Final wait for URL
//            wait.until(ExpectedConditions.urlContains("/checkout-step-two.html"));
        }
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageElement));
        System.out.println("Waiting the error message display:" + element.getText());
        return element.getText();
    }

}
