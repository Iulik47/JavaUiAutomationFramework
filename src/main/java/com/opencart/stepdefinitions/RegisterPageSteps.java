package com.opencart.stepdefinitions;

import com.opencart.managers.DataFakerManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class RegisterPageSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    RegisterPage registerPage = new RegisterPage(driver);
    @When("the Registration form is completed with valid random data")
    public void theRegistrationFormIsCompletedWithValidRandomData() {
        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String randomEmail = DataFakerManager.getRandomEmail("bazalt", "@mug.dev");
        String password = DataFakerManager.getRandomPassword(21, 24);

        registerPage.fillInTheRegisterForm(firstName, lastName, randomEmail, password);
        
    }

    @And("the privacy button is enabled")
    public void thePrivacyButtonIsEnabled() {
        try {
            registerPage.switchOnThePrivacyToggle(driver);
        } catch (InterruptedException e) {
            System.out.println("Error!");
        }

    }

    @And("continueButton is clicked")
    public void continuebuttonIsClicked() throws InterruptedException {
        registerPage.clickOnContinueButton();
    }
}
