package com.selenium.orangehrm.tests;

import com.selenium.orangehrm.pages.DashboardPage;
import com.selenium.orangehrm.pages.LeavePage;
import com.selenium.orangehrm.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Test for verifying Leave module navigation and visibility.
 */
public class LeaveTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testMyLeavePageNavigation() {
        // Step 1: Login to OrangeHRM
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("Admin", "admin123");

        // Step 2: Navigate to Leave → My Leave
        LeavePage leave = new LeavePage(driver);
        leave.navigateToMyLeave();

        // Step 3: Validate the Leave List is visible or shows 'No Records Found'
        boolean listVisible = leave.isLeaveListDisplayed();
        boolean noRecords = leave.hasNoRecordsMessage();

        Assert.assertTrue(listVisible || noRecords,
                "Expected Leave list or 'No Records Found' message to appear, but neither was found.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
