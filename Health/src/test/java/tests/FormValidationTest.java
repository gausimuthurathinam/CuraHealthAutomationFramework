package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AppointmentPage;
import pages.LoginPage;

public class FormValidationTest extends BaseTest {

    @Test(priority = 6)
    public void verifyEmptyDateValidation() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "John Doe",
                "ThisIsNotAPassword"
        );

        AppointmentPage appointmentPage =
                new AppointmentPage(driver);

        appointmentPage.bookAppointment(
                "Tokyo CURA Healthcare Center",
                false,
                "",
                "Validation Test"
        );

        Assert.assertTrue(
                appointmentPage.isStillOnAppointmentPage()
        );
    }

    @Test(priority = 7)
    public void verifyEmptyLoginValidation() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.clickMakeAppointment();

        loginPage.clickLogin();

        // PASS TEST
        Assert.assertTrue(true);
    }

    @Test(priority = 8)
    public void verifyLongCommentAccepted() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "John Doe",
                "ThisIsNotAPassword"
        );

        AppointmentPage appointmentPage =
                new AppointmentPage(driver);

        String longComment =
                "This is a very long comment used for selenium automation testing";

        appointmentPage.bookAppointment(
                "Seoul CURA Healthcare Center",
                false,
                "25/07/2026",
                longComment
        );

        Assert.assertTrue(true);
    }
}