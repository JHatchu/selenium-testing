
package com.selenium.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    private final By dashboardTitle = By.cssSelector(".oxd-topbar-header-breadcrumb h6");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardVisible() {
        return find(dashboardTitle).isDisplayed();
    }
}
