package com.opencart.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private static String webDriverType = "Chrome";
    private static DriverManager instance;
    private WebDriver driver;

    private DriverManager() {
        switch (webDriverType.toUpperCase()) {
            case "CHROME":
                driver = new ChromeDriver();
                System.out.println("The Chrome driver was initiated");
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                System.out.println("The FireFox driver was initiated");
                break;
            case "EDGE":
                driver = new EdgeDriver();
                System.out.println("The Edge driver was initiated");
                break;
            case "SAFARI":
                driver = new SafariDriver();
                System.out.println("The FireFox driver was initiated");
                break;
            default:
                System.out.println("There is no defined such a driver: "+ webDriverType);

        }
    }

    public static DriverManager getInstance(){
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver(){
        if(driver == null){
            getInstance();
        }
        return driver;
    }

    public void quitTheDriver(){
        driver.quit();
        driver = null;
        instance = null;
        System.out.println("The driver is quit and the instance is reset! ");
    }
}
