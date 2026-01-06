package org.davinatw.WebUITest.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.davinatw.WebUITest.BaseTest;
import org.davinatw.WebUITest.page.CartPage;
import org.davinatw.WebUITest.page.CheckoutCompletePage;
import org.davinatw.WebUITest.page.CheckoutFirstPage;
import org.davinatw.WebUITest.page.CheckoutSecondPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutStepDef extends BaseTest {
    CartPage cartPage;
    CheckoutFirstPage checkoutFirstPage;
    CheckoutSecondPage checkoutSecondPage;
    CheckoutCompletePage checkoutCompletePage;

    @And("user clicks the checkout button")
    public void clickCheckoutButton(){
        cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();
    }

    @And("user is on checkout first page")
    public void isOnCheckoutFirstPage(){
        checkoutFirstPage = new CheckoutFirstPage(driver);
        assertTrue(checkoutFirstPage.isCheckoutFirstPage());
    }

    @And("user input first name with {string}")
    public void inputFirstNameWith(String firstName){
//        checkoutFirstPage = new CheckoutFirstPage(driver);
        checkoutFirstPage.inputFirstName(firstName);

    }

    @And("user input last name with {string}")
    public void inputsLastNameWith(String lastName){
        checkoutFirstPage.inputLastName(lastName);
    }

    @And("user input Zip code with {string}")
    public void inputZipCodeWith(String zipCode){
        checkoutFirstPage.inputPostCode(zipCode);
    }

    @And("user clicks the continue button")
    public void clickContinueButton(){
        checkoutFirstPage.clickContinueButton();
    }

    @Then("user is on checkout second page")
    public void isOnCheckoutSecondPage(){
        checkoutSecondPage = new CheckoutSecondPage(driver);
        assertTrue(checkoutSecondPage.isOnCheckoutSecondPage());
    }
    @And("user sees the total price and the items to checkout")
    public void seesTotalPriceAndItemsToCheckout(){
        assertEquals("Total: $32.39", checkoutSecondPage.textFinalPrice());
    }

    @When("user clicks the finish button")
    public void clickFinishButton(){
        checkoutSecondPage.clickFinishButton();
    }

    @Then("user sees the checkout complete message")
    public void seesTheCheckoutCompleteMessage(){
        checkoutCompletePage = new CheckoutCompletePage(driver);
        assertTrue(checkoutCompletePage.validateCheckoutCompletePage());
    }

    @And("user clicks the continue shopping button")
    public void clickContinueShoppingButton(){
        cartPage = new CartPage(driver);
        cartPage.clickContinueShoppingButton();
    }

    @Then("the system displays the error message {string}")
    public void displaysTheErrorMessage(String errorMessage){
        assertEquals(errorMessage, checkoutFirstPage.getErrorMessage());

    }
}
