package org.davinatw.WebUITest.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.davinatw.WebUITest.BaseTest;
import org.davinatw.WebUITest.page.CartPage;
import org.davinatw.WebUITest.page.HomePage;

import static org.junit.Assert.assertTrue;

public class CartStepDef extends BaseTest {
    HomePage homePage;
    CartPage cartPage;

    @And("user clicks cart icon button on the first item")
    public void clickCartIconButtonOnTheFirstItem() {
        homePage = new HomePage(driver);
        homePage.clickAddToCart();
    }

    @And("user clicks cart icon button")
    public void clickCartIconButton(){

        homePage.clickCartIconButton();
    }

    @And("user is on cart page")
    public void isOnCartPage(){
        cartPage = new CartPage(driver);

        assertTrue(cartPage.isCartPage());
    }

    @When("user clicks remove button")
    public void clickRemoveButton(){
        cartPage.removeItemFromCart();
    }

    @Then("the cart item \"Sauce Labs Backpack\" should not be visible")
    public void validateItemNotExist(){
        assertTrue(cartPage.isCartEmpty());
    }



}
