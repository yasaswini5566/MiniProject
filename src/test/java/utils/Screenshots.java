package utils;

import org.openqa.selenium.*;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class Screenshots {

    public static void takeScreenshot(WebDriver driver, String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src,
                    new File("C:\\Users\\2487721\\IdeaProjects\\MiniProject\\screenshots\\" + name + ".png"));
        } catch (Exception e) {
            System.out.println("Screenshot failed");
        }
    }
}