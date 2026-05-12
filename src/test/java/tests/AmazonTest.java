package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.Amazon;
import utils.ExcelStore;
import utils.ExcelUtils;
import utils.Screenshots;

import java.time.Duration;
import java.util.List;

public class AmazonTest extends BaseTest {
    Amazon home;
    SoftAssert softAssert = new SoftAssert();
    @Test(priority = 0)
    void verifyHomePageTitle() {
        String expected = "Flipcart"; // intentionally wrong
        String actual = driver.getTitle();

        softAssert.assertTrue(actual.contains(expected),
                "Home page title mismatch");

        ExcelStore.writeResult(
                "Verify Home Page Title",
                "Title should contain Amazon",
                actual,
                actual.contains(expected) ? "PASS" : "FAIL"
        );

        softAssert.assertAll();
    }
    @Test(dependsOnMethods = "verifyHomePageTitle")
    void verifyAmazonLogoDisplayed() {
        WebElement logo = driver.findElement(By.id("nav-logo-sprites"));

        softAssert.assertTrue(logo.isDisplayed());

        ExcelStore.writeResult(
                "Verify Amazon Logo Displayed",
                "Amazon logo should be displayed",
                "Amazon logo is displayed",
                "PASS"
        );
    }
    @Test(priority = 1)
    void verifySearchBoxEnabled() {
        try {
            WebElement searchBox =
                    driver.findElement(By.id("twotabsearchtextbox"));
            Screenshots.takeScreenshot(driver, "SearchBox");
            softAssert.assertTrue(searchBox.isEnabled());
            ExcelStore.writeResult(
                    "Verify Search Box Enabled",
                    "Search box should be enabled",
                    "Search box is enabled",
                    "PASS"
            );
        } catch (Exception e) {
            ExcelStore.writeResult(
                    "Verify Search Box Enabled",
                    "Search box should be enabled",
                    "Search box not enabled",
                    "FAIL"
            );
            softAssert.fail();
        }
    }
    @Test(priority = 2)
    void search() {
        String expected = "Search results should be displayed";
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
            String searchText = ExcelUtils.getCellData("SEARCH_TEXT");
            searchBox.sendKeys(searchText, Keys.ENTER);
            Screenshots.takeScreenshot(driver, "SearchResults");
            WebElement results =
                    driver.findElement(By.xpath("//div[@class='sg-col-inner']"));
            System.out.println("search text: "+ results.getText());
            softAssert.assertTrue(results.isDisplayed());
            ExcelStore.writeResult(
                    "Search Mobile Phones",
                    expected,
                    "Search results displayed",
                    "PASS"
            );
        } catch (Exception e) {
            ExcelStore.writeResult(
                    "Search Mobile Phones",
                    expected,
                    "Search failed",
                    "FAIL"
            );
            softAssert.fail();
        }
    }
    @Test(priority = 4, dependsOnMethods = "search")
    void verifySortOptions() {
        try {
            WebElement sortDropdown =
                    driver.findElement(By.id("s-result-sort-select"));
            Select select = new Select(sortDropdown);
            int count = select.getOptions().size();
            softAssert.assertTrue(count >= 4);
            ExcelStore.writeResult(
                    "Verify Sort Options",
                    "Minimum 4 sort options",
                    "Sort options count: " + count,
                    "PASS"
            );
        } catch (Exception e) {
            ExcelStore.writeResult(
                    "Verify Sort Options",
                    "Minimum 4 sort options",
                    "Sort options missing",
                    "FAIL"
            );
            softAssert.fail();
        }
    }
    @Test(priority = 5)
    void sort() {

        try {
            WebElement sortDropdown =
                    driver.findElement(By.id("s-result-sort-select"));

            Select select = new Select(sortDropdown);
            select.selectByVisibleText("Newest Arrivals");

            Screenshots.takeScreenshot(driver, "SortNewest");

            String selected = select.getFirstSelectedOption().getText();
            softAssert.assertEquals(selected, "Newest Arrivals");

            ExcelStore.writeResult(
                    "Sort By Newest Arrivals",
                    "Newest Arrivals should be selected",
                    selected,
                    "PASS"
            );
        } catch (Exception e) {
            ExcelStore.writeResult(
                    "Sort By Newest Arrivals",
                    "Newest Arrivals should be selected",
                    "Sort failed",
                    "FAIL"
            );
            softAssert.fail();
        }
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}