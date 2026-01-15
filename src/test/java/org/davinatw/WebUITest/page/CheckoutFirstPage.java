package org.davinatw.WebUITest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutFirstPage {
    WebDriver driver;

    By firstNameInput = By.xpath("//*[@id=\"first-name\"]");
    By lastNameInput = By.xpath("//*[@id=\"last-name\"]");
    By postCodeInput = By.xpath("//*[@id=\"postal-code\"]");
    By continueButton = By.xpath("//*[@id=\"continue\"]");

    By errorMessage = By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]");

    public CheckoutFirstPage(WebDriver driver){

        this.driver = driver;
    }

    public boolean isCheckoutFirstPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // This waits until the URL actually contains /cart.html
            System.out.println("True URL:" + driver.getCurrentUrl());
            return wait.until(ExpectedConditions.urlContains("/cart.html"));
        } catch (Exception e) {
            // If it times out, print the actual URL to help you debug
            System.out.println("Actual URL found: " + driver.getCurrentUrl());
            return false;
        }
    }

    public void inputFirstName(String firstName){

        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void inputLastName(String lastName){

        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void inputPostCode(String postCode){
        driver.findElement(postCodeInput).sendKeys(postCode);
    }

    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }

    public String getErrorMessage(){
        return driver.findElement(errorMessage).getText();
    }

}
