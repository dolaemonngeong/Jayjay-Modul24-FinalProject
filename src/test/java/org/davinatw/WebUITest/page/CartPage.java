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

    public boolean isCartEmpty() {
        List<WebElement> items = driver.findElements(By.className("cart_item"));

        // Returns true if the size is 0
        if(items.size() == 0){
            System.out.println("cart is empty");
            return true;
        }else{
            System.out.println("cart is not empty. w/ item: "+ items.get(0).getText() + "\n | the item size: "+ items.size());
            return false;
        }
    }

    public void removeItemFromCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By removeButtonLocator = By.xpath("//button[text()='Remove']");

        // 1. Get current item count BEFORE clicking
        int initialCount = driver.findElements(By.className("cart_item")).size();

        if (initialCount == 0) {
            System.out.println("Cart is already empty.");
            return;
        }

        try {
            // 2. Try Standard Click
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(removeButtonLocator));
            button.click();

            // 3. CRITICAL: Wait for the item count to actually DROP
            // This ensures the step doesn't finish until the removal is successful
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className("cart_item"), initialCount));

        } catch (Exception e) {
            System.out.println("Standard remove click failed (Item count didn't drop). Retrying with JavaScript...");

            // 4. Fallback: Force Click with JavaScript
            WebElement button = driver.findElement(removeButtonLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

            // 5. Wait again for the count to drop
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className("cart_item"), initialCount));
        }

//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(removeButtonFirstItem));
//
//        try{
//            System.out.println("Removing item from cart with standard click...");
//            element.click();
//        }catch (Exception e){
//
//            System.out.println("Standard click failed to redirect, trying JavaScript click.");
//            // Backup: JavaScript click
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].click();", element);
//        }
    }

    public void clickContinueShoppingButton(){
        driver.findElement(continueShoppingButton).click();
    }

    public void clickCheckoutButton(){

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
