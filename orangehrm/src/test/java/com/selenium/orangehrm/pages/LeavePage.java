package com.selenium.orangehrm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LeavePage extends BasePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By dashboardLoaded = By.xpath("//h6[text()='Dashboard']");
    private final By leaveMenu = By.xpath("//a[contains(@href,'viewLeaveModule')]");
    private final By myLeaveLink = By.xpath("//a[contains(@href,'viewMyLeaveList')]");
    private final By leaveListTable = By.cssSelector(".oxd-table");
    private final By noRecordsLabel = By.xpath("//span[text()='No Records Found']");
private final By myLeaveTab = By.xpath("//li/a[contains(.,'My Leave')]");
    public LeavePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateToLeave() {
        // Wait for dashboard to finish loading before clicking sidebar
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardLoaded));

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(leaveMenu));

        // Scroll into view and click via JS to bypass overlay
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

 public void navigateToMyLeave() {
    navigateToLeave();
    wait.until(ExpectedConditions.presenceOfElementLocated(myLeaveTab));
    WebElement element = driver.findElement(myLeaveTab);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
}

    public boolean isLeaveListDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(leaveListTable));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasNoRecordsMessage() {
        return !driver.findElements(noRecordsLabel).isEmpty();
    }
}
