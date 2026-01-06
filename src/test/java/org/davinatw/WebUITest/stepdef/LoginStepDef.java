package org.davinatw.WebUITest.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.davinatw.WebUITest.BaseTest;
import org.davinatw.WebUITest.page.LoginPage;

import static org.junit.Assert.assertTrue;


public class LoginStepDef extends BaseTest {
    LoginPage loginPage;

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
    }

    @And("user input username with {string}")
    public void userInputUsernameWith(String username) {
        loginPage.inputUserName(username);
    }

    @And("user input password with {string}")
    public void userInputPasswordWith(String password) {

        loginPage.inputPassword(password);
    }

    @When("user click login button")
    public void userClickLoginButton() {

        loginPage.clickLoginButton();
    }

    @Then("user see error message {string}") // Fixed spelling
    public void userSeeErrorMessage(String errorMessage) {
        assertTrue(loginPage.validateErrorMessage(errorMessage));
    }

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.inputUserName("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLoginButton();
    }


}
