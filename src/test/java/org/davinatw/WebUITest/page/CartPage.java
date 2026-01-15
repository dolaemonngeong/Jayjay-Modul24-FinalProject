package org.davinatw.WebUITest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
//        return driver.getCurrentUrl().contains("/cart.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // This waits until the URL actually contains /cart.html
            System.out.println("True URL " + driver.getCurrentUrl());
            return wait.until(ExpectedConditions.urlContains("/cart.html"));
        } catch (Exception e) {
            // If it times out, print the actual URL to help you debug
            System.out.println("Actual URL found: " + driver.getCurrentUrl());
            return false;
        }
    }

    public boolean isCartEmpty() {
        // Look for all cart items
//        List<WebElement> items = driver.findElements(By.className("cart_item"));
//        return items.isEmpty(); // Returns true if size is 0
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Wait until any existing cart items are gone
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("cart_item")));
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
