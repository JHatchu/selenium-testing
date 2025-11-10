package com.selenium.orangehrm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Handles navigation and validation for Leave module
 */
public class LeavePage extends BasePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By leaveMenu = By.xpath("//a[contains(@href,'viewLeaveModule')]");
    private final By myLeaveLink = By.xpath("//a[contains(@href,'viewMyLeaveList') or //span[text()='My Leave']]");
    private final By leaveListTable = By.cssSelector(".oxd-table");
    private final By noRecordsLabel = By.xpath("//span[text()='No Records Found']");

    // ✅ Constructor
    public LeavePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ✅ Navigate to the Leave module
    public void navigateToLeave() {
        click(leaveMenu);
    }

    // ✅ Navigate to “My Leave” section
    public void navigateToMyLeave() {
        navigateToLeave();
        click(myLeaveLink);
    }

    // ✅ Verify the leave list table is visible
    public boolean isLeaveListDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(leaveListTable));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Check if “No Records Found” message appears
    public boolean hasNoRecordsMessage() {
        return !driver.findElements(noRecordsLabel).isEmpty();
    }

    // ✅ Custom click with JS fallback
    protected void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (ElementClickInterceptedException ignored) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
