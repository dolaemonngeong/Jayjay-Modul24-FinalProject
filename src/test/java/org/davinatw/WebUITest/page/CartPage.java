package org.davinatw.WebUITest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    WebDriver driver;

    By removeButtonFirstItem = By.id("remove-sauce-labs-backpack");
    By continueShoppingButton = By.xpath("//*[@id=\"continue-shopping\"]");

    By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver){

        this.driver = driver;
    }

    public boolean isCartPage(){
        return driver.getCurrentUrl().contains("/cart.html");
    }

    public boolean isCartEmpty() {
        // Look for all cart items
        List<WebElement> items = driver.findElements(By.className("cart_item"));
        return items.isEmpty(); // Returns true if size is 0
    }

    public void removeItemFromCart(){
        driver.findElement(removeButtonFirstItem).click();
    }

    public void clickContinueShoppingButton(){
        driver.findElement(continueShoppingButton).click();
    }

    public void clickCheckoutButton(){
        driver.findElement(checkoutButton).click();
    }
}
