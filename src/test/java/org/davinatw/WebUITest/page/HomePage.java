package org.davinatw.WebUITest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class HomePage {
    WebDriver driver;

    By headerDisplay = By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div");
    By burgerMenu = By.id("react-burger-menu-btn");

    private By logOutNavBar = By.xpath("//*[@id=\"logout_sidebar_link\"]");

    private By sortDropdown = By.cssSelector("[data-test='product-sort-container']");

    private By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private By removeButton = By.id("remove-sauce-labs-backpack");

    private By cartBadge = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");

    private By inventoryItemNames = By.cssSelector(".inventory_item_name");
    private By inventoryItemPrice = By.cssSelector(".inventory_item_price");


    public HomePage(WebDriver driver){

        this.driver = driver;
    }

    public boolean validateHeaderDisplayed(){

        return driver.findElement(headerDisplay).isDisplayed();
    }


    public void selectSortOption(String optionText) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(optionText);
    }

    public void clickAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Locate the button
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

        try {
            // 2. Try Standard Click
            button.click();

            // 3. VERIFICATION: Wait for the button to disappear or change
            // This ensures we don't move to the next step until the click effectively worked.
            // We wait for the "Add to Cart" button to become invisible OR the "Remove" button to appear.
            wait.until(ExpectedConditions.invisibilityOfElementLocated(addToCartButton));

        } catch (Exception e) {
            System.out.println("Standard 'Add to Cart' click failed. Retrying with JavaScript...");

            // 4. Fallback: JS Click
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(addToCartButton));

            // 5. Wait again for the state to change
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.invisibilityOfElementLocated(addToCartButton),
                    ExpectedConditions.visibilityOfElementLocated(removeButton)
            ));
        }
    }

    public boolean validateRemoveButtonDisplayed() {
        return driver.findElement(removeButton).isDisplayed();
    }

    public String getCartItemCount() {
        return driver.findElement(cartBadge).getText();
    }

    public String getFirstItemName() {
        List<WebElement> items = driver.findElements(inventoryItemNames);
        return items.get(0).getText();
    }

    public String getLastItemName() {
        List<WebElement> items = driver.findElements(inventoryItemNames);
        return items.get(items.size() - 1).getText();
    }

    public String getFirstItemPrice() {
        List<WebElement> items = driver.findElements(inventoryItemPrice);
        return items.get(0).getText();
    }

    public String getLastItemPrice() {
        List<WebElement> items = driver.findElements(inventoryItemPrice);
        return items.get(items.size() - 1).getText();
    }

    public void clickBurgerMenu() {
        driver.findElement(burgerMenu).click();

    }

    public void clickLogOutButton(){

        // 1. Ensure the element is visible to the eye, not just in the code
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));

        // 2. Use JavaScript click as a fallback to ensure the click registers
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", logoutLink);
    }

    public void clickCartIconButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Use the class name for the cart link in SauceDemo
        By cartIcon = By.className("shopping_cart_link");

        // 1. Wait for visibility
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon));
        System.out.println("Cart icon is visible, proceeding to click.");

        try {
            // 2. Try standard click
            element.click();
            // 3. Wait for the URL to actually change to cart.html
            wait.until(ExpectedConditions.urlContains("/cart.html"));
        } catch (Exception e) {
            System.out.println("Standard click failed to redirect, trying JavaScript click.");
            // 4. Backup: JavaScript click
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            // 5. Final wait for URL
            wait.until(ExpectedConditions.urlContains("/cart.html"));
        }
    }

    public boolean logOutNavBarDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            // Wait for it to be visible to the eye, not just in the code
            return wait.until(ExpectedConditions.visibilityOfElementLocated(logOutNavBar)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


}
