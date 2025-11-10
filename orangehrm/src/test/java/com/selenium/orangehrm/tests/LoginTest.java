
package com.selenium.orangehrm.tests;

import com.selenium.orangehrm.pages.DashboardPage;
import com.selenium.orangehrm.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
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
    public void testLoginSuccess() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("Admin", "admin123");
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardVisible(), "Login failed!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
