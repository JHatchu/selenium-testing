
package com.selenium.bmi.steps;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.testng.Assert.assertTrue;

public class BmiSteps {
    WebDriver driver;

    @Given("I open the BMI calculator page")
    public void open_page() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.calculator.net/bmi-calculator.html");
    }

    @When("I enter age {string} height {string} and weight {string}")
    public void enter_data(String age, String height, String weight) {
        driver.findElement(By.id("cage")).clear();
        driver.findElement(By.id("cage")).sendKeys(age);
        driver.findElement(By.id("cheightmeter")).clear();
        driver.findElement(By.id("cheightmeter")).sendKeys(height);
        driver.findElement(By.id("ckg")).clear();
        driver.findElement(By.id("ckg")).sendKeys(weight);
    }

    @When("I click Calculate")
    public void click_calculate() {
        driver.findElement(By.xpath("//input[@value='Calculate']")).click();
    }

    @Then("I should see the BMI result displayed")
    public void verify_result() {
        WebElement result = driver.findElement(By.cssSelector("div.bigtext"));
        assertTrue(result.isDisplayed());
        driver.quit();
    }
}
