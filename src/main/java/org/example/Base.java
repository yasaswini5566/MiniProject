package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
    public static void main(String[] args) {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
        WebElement search=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        search.sendKeys("mobile smartphones under 30000");
        search.sendKeys(Keys.ENTER);
        WebElement searchBox=driver.findElement(By.xpath("//*[@id=\"search\"]/span/div/h1/div/div[1]/div/h2"));
        System.out.println("The result: "+searchBox.getText());
    }
}