package org.davinatw.WebUITest.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.davinatw.WebUITest.BaseTest;
import org.davinatw.WebUITest.page.HomePage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class HomeStepDef extends BaseTest {
    HomePage homePage;

    @Given("user is on home page")
    public void userIsOnHomepage() {
        homePage = new HomePage(driver);
        assertTrue(homePage.validateHeaderDisplayed());
    }

    @Then("user should be directed to home page")
    public void validateUserIsOnHomepage() {
        homePage = new HomePage(driver);
        assertTrue(homePage.validateHeaderDisplayed());
    }

    @When("user clicks add to cart button on the first item")
    public void userClickAddToCartButton() {
        homePage.clickAddToCart();
    }

    @Then("add to cart button changes to remove button")
    public void addToCartButtonChangesToRemove() {

        assertTrue(homePage.validateRemoveButtonDisplayed());
    }

    @Then("cart icon shows item count")
    public void cartIconShowsItemCount() {

        assertEquals("1", homePage.getCartItemCount());
    }



    @When("user clicks {string}")
    public void clickSort(String optionText){
        homePage.selectSortOption(optionText);
    }
    @Then("user can see the first product named {string}")
    public void validateFirstProductName(String firstProductName){
        assertEquals(firstProductName, homePage.getFirstItemName());
    }

    @And("user can see the last product named {string}")
    public void validateLastProductName(String lastProductName){
        assertEquals(lastProductName, homePage.getLastItemName());
    }

    @Then("user can see the first product with price {string}")
    public void validateFirstProductPrice(String firstProductPrice){
        assertEquals(firstProductPrice, homePage.getFirstItemPrice());
    }

    @And("user can see the last product with price {string}")
    public void validateLastProductPrice(String lastProductPrice){
        assertEquals(lastProductPrice, homePage.getLastItemPrice());
    }



}
