package org.davinatw.WebUITest.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.davinatw.WebUITest.BaseTest;
import org.davinatw.WebUITest.page.HomePage;
import org.davinatw.WebUITest.page.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogOutStepDef extends BaseTest {
    HomePage homePage;

    @When("user click burger menu")
    public void clickBurgerMenu() {
        homePage = new HomePage(driver);
        homePage.clickBurgerMenu();
    }

    @And("user click logout button")
    public void clickLogoutNavBar(){
        // Ensure we wait for the animation to finish
        homePage.clickLogOutButton();
    }

    @And("logout button displayed")
    public void logoutButtonDisplayed() {
        assertTrue(homePage.logOutNavBarDisplayed());
    }

    @Then("user should be directed to login page")
    public void validateToLoginPage() {
        // 1. Wait up to 10 seconds for the URL to change to the base URL
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));

        // 2. Now perform the assertion
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/", currentUrl);
    }
}
