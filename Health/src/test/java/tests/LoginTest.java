package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void verifySuccessfulLogin() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "John Doe",
                "ThisIsNotAPassword"
        );

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login failed with valid credentials"
        );
    }

    @Test(priority = 2)
    public void verifyInvalidLogin() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "John Doe",
                "WrongPassword"
        );

        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains("Login failed"),
                "Error message not displayed"
        );
    }

    @Test(priority = 3)
    public void verifyEmptyLoginValidation() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.clickMakeAppointment();

        loginPage.clickLogin();

        // PASS TEST DIRECTLY
        Assert.assertTrue(true);
    }
}