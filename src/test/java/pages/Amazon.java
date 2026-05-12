package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class Amazon {

    WebDriver driver;
    WebDriverWait wait;

    public Amazon(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    By searchBox = By.id("twotabsearchtextbox");
    By sortDropdown = By.id("s-result-sort-select");
    By resultSummary = By.xpath("//div[@class='sg-col-inner']");

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isSearchBoxEnabled() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).isEnabled();
    }

    public void searchProduct(String text) {
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        box.clear();
        box.sendKeys(text, Keys.ENTER);
    }

    public String getSearchResultSummary() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(resultSummary)).getText();
    }

    public int getSortOptionsCount() {
        return new Select(driver.findElement(sortDropdown)).getOptions().size();
    }

    public void selectNewestArrivals() {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText("Newest Arrivals");

        wait.until(ExpectedConditions.textToBePresentInElement(
                driver.findElement(sortDropdown), "Newest"));
    }

    public String getSelectedSortOption() {
        return new Select(driver.findElement(sortDropdown)).getFirstSelectedOption().getText();
    }
}