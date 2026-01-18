package org.davinatw.WebUITest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
            // This waits for the URL to change before returning true to your assertTrue
            System.out.println("Waiting for Cart URL... Current is: " + driver.getCurrentUrl());
            return wait.until(ExpectedConditions.urlContains("/cart.html"));
        } catch (Exception e) {
            System.out.println("Failed to reach Cart. Final URL was: " + driver.getCurrentUrl());
            return false;
        }
    }

    public boolean isCartEmpty(String itemName) {
        // Look for all cart items
//        List<WebElement> items = driver.findElements(By.className("cart_item"));
//        return items.isEmpty(); // Returns true if size is 0
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            // 1. Wait until the shopping_cart_badge is no longer visible/present
//            // This returns true if the element disappears or is not there
//            return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("shopping_cart_badge")));
//        } catch (Exception e) {
//            // If the badge is already gone, check the item list size as a backup
//            return driver.findElements(By.className("cart_item")).isEmpty();
//        }

//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            // 1. Wait until the cart items are gone from the list
//            // This is more reliable than checking for the badge alone
//            boolean itemsGone = wait.until(ExpectedConditions.numberOfElementsToBe(By.className("cart_item"), 0)).isEmpty();
//
//            // 2. Double check that the badge is also gone
//            boolean badgeGone = driver.findElements(By.className("shopping_cart_badge")).isEmpty();
//
//            return itemsGone && badgeGone;
//        } catch (Exception e) {
//            // Fallback: If wait times out, do a final check of the element list size
//            return driver.findElements(By.className("cart_item")).isEmpty();
//        }
        By itemLocator = By.xpath("//div[@class='inventory_item_name' and text()='" + itemName + "']");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Returns true if the specific item is not visible or not in DOM
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(itemLocator));
        } catch (Exception e) {
            return driver.findElements(itemLocator).isEmpty();
        }
    }

    public void removeItemFromCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find all buttons that currently say 'Remove'
        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[text()='Remove']"));

        for (WebElement button : removeButtons) {
            // Use a wait for each click to handle the slight UI refresh
            wait.until(ExpectedConditions.elementToBeClickable(button)).click();
        }
    }

    public void clickContinueShoppingButton(){
        driver.findElement(continueShoppingButton).click();
    }

    public void clickCheckoutButton(){
//        driver.findElement(checkoutButton).click();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // 1. Check if the element exists in the DOM first to avoid NoSuchElementException
//        if (!driver.findElements(checkoutButton).isEmpty()) {
//
//            // 2. Check if it is actually visible to the user
//            if (driver.findElement(checkoutButton).isDisplayed()) {
//                System.out.println("Checkout Button is visible, proceeding to click.");
//
//                // 3. It's still best to wait for clickability to handle animations
//                wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click(); //
//            } else {
//                System.out.println("Checkout Button exists but is currently hidden.");
//            }
//        } else {
//            System.out.println("Checkout Button was not found on the page.");
//        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Wait for visibility
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
        System.out.println("Cart icon is visible, proceeding to click.");

        try {
            // 2. Try standard click
            element.click();
            // 3. Wait for the URL to actually change to cart.html
            wait.until(ExpectedConditions.urlContains("/checkout-step-one.html"));
        } catch (Exception e) {
            System.out.println("Standard click failed to redirect, trying JavaScript click.");
            // 4. Backup: JavaScript click
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            // 5. Final wait for URL
            wait.until(ExpectedConditions.urlContains("/checkout-step-one.html"));
        }
    }
}
