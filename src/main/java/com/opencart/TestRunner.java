package com.opencart;

import com.opencart.managers.DataFakerManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.AccountCreatedPage;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.openqa.selenium.WebDriver;


public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        // Define a driver object that will be used for future actions.
        WebDriver driver = DriverManager.getInstance().getDriver();

        //  for (int i = 0; i < 4; i++){......}   pentru a repeta tot ciclul(registrare, logout) de 4 ori in cazul dat,
        //           ( punem tot codul pina la  driver.quit()) in interiorul   la ciclul for

        driver.get("https://andreisecuqa.host/");

        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String randomEmail = DataFakerManager.getRandomEmail("bazalt", "@mug.dev");
        String password = DataFakerManager.getRandomPassword(21, 24);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillInTheRegisterForm(firstName, lastName, randomEmail, password);
        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        accountCreatedPage.logoutFromTheAccount();
        Thread.sleep(1000);
        System.out.println(driver.getCurrentUrl());


        driver.quit();
        System.out.println("The execution was finished");


    }
}