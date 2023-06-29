package com.opencart;

import com.opencart.managers.DataFakerManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class RegistrationFlowTestSuite {

    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    static int counter = 0;

    @BeforeEach
    public void executeTheCodeBeforeEachTestFromThisClass() {
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://andreisecuqa.host/");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        counter++;
        System.out.println("The test number " + counter + " started!");
    }

    @Test
    @DisplayName("The URL contains success keyword after registration with valid data")
    public void registerFlowRedirectsTheUserToTheCorrectUrl() throws InterruptedException {

        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String randomEmail = DataFakerManager.getRandomEmail("bazalt", "@mug.dev");
        String password = DataFakerManager.getRandomPassword(21, 24);

        registerPage.fillInTheRegisterForm(firstName, lastName, randomEmail, password);
        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();

        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("/index.php?route=account/success&language");
        String errorMessage = "The URL " + driver.getCurrentUrl() + "contains success keyword";
        Assertions.assertTrue(urlContainsTheCorrectKeyWords, errorMessage);

    }

    @Test
    @DisplayName("The URL contains register keyword when privacy policy is not accepted")
    public void registerFlowIsBlockedByPrivacyToggleThatIsNotAccepted() throws InterruptedException {

        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String randomEmail = DataFakerManager.getRandomEmail("bazalt", "@mug.dev");
        String password = DataFakerManager.getRandomPassword(21, 24);

        registerPage.fillInTheRegisterForm(firstName, lastName, randomEmail, password);
        // Do not click on Privacy Toggle
        // registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();

        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsRegisterKeyword = driver.getCurrentUrl().contains("register");
        String errorMessage = "The URL " + driver.getCurrentUrl() + " does not contain the register keyword";
        Assertions.assertTrue(urlContainsRegisterKeyword, errorMessage);
    }

    @AfterEach
    public void afterEachTestExecution() {
        DriverManager.getInstance().quitTheDriver();
        System.out.println("The test case number " + counter + " is terminated");
    }
}
