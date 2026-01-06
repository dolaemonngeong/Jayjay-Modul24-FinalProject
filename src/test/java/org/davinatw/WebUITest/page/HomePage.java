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
    By aboutNavBar = By.id("about_sidebar_link");
    private By logOutNavBar = By.xpath("//*[@id=\"logout_sidebar_link\"]");

    private By sortDropdown = By.cssSelector("[data-test='product-sort-container']");

    private By addToCartButton = By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]");
    private By removeButton = By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]");
    By cartIcon = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    private By cartBadge = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");

    private By inventoryItemNames = By.cssSelector(".inventory_item_name");
    private By inventoryItemPrice = By.cssSelector(".inventory_item_price");


    public HomePage(WebDriver driver){

        this.driver = driver;
    }

    public boolean validateHeaderDisplayed(){

        return driver.findElement(headerDisplay).isDisplayed();
    }

    public void clickAboutNavBar(){
//        driver.findElement(burgerMenu).click();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//
//        wait.until(ExpectedConditions.elementToBeClickable(aboutNavBar));
//        driver.findElement(aboutNavBar).click();
        // 1. Click the Burger Menu
        driver.findElement(burgerMenu).click();

        // 2. Wait for the 'About' link to be visible and clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement aboutLink = wait.until(ExpectedConditions.elementToBeClickable(aboutNavBar));

        try {
            // Try standard click first
            aboutLink.click();
            System.out.println("Clicked about nav bar");
        } catch (Exception e) {
            // If standard click fails, use JavaScript click (more powerful)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", aboutLink);
            System.out.println("not clicked about nav bar");
        }
    }



    public void selectSortOption(String optionText) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(optionText);
    }

    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
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
        driver.findElement(cartIcon).click();
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
