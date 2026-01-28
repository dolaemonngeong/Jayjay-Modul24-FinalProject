package org.davinatw.WebUITest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    By usernameInputText = By.cssSelector("input#user-name");
    By passwordInputText = By.xpath("//*[@id=\"password\"]");
    By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    public void inputUserName(String userName){

        driver.findElement(usernameInputText).sendKeys(userName);
    }

    public void inputPassword(String password){

        driver.findElement(passwordInputText).sendKeys(password);
    }

    public void clickLoginButton(){

        driver.findElement(loginButton).click();
    }

    public boolean validateErrorMessage(String errorMessage){

        return driver.getPageSource().contains(errorMessage);
    }


}
