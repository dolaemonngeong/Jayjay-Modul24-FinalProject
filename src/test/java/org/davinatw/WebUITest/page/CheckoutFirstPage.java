package org.davinatw.WebUITest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        return driver.getCurrentUrl().contains("/checkout-step-one.html");
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
