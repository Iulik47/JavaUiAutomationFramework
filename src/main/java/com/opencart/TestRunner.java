package com.opencart;

import com.opencart.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class TestRunner {
    public static void main(String[] args) {
        // Define a driver object that will be used for future actions.
       WebDriver driver = DriverManager.getInstance().getDriver();

       driver.get("https://999.md/ru/");

       String currentWindowName = driver.getWindowHandle();

       driver.switchTo().newWindow(WindowType.TAB);

       driver.get("https://mvnrepository.com/search?q=selenium+api");

       driver.close();

       driver.switchTo().window(currentWindowName);

       driver.get("https://unsplash.com/s/photos/canada");

       driver.quit();
        System.out.println("The execution was finished");

    }
}