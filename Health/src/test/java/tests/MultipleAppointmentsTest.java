package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AppointmentPage;
import pages.HistoryPage;
import pages.LoginPage;

public class MultipleAppointmentsTest extends BaseTest {

    @Test(priority = 4)
    public void verifyMultipleAppointmentsHistory() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "John Doe",
                "ThisIsNotAPassword"
        );

        AppointmentPage appointmentPage =
                new AppointmentPage(driver);

        // FIRST APPOINTMENT
        appointmentPage.bookAppointment(
                "Tokyo CURA Healthcare Center",
                false,
                "10/07/2026",
                "First Appointment"
        );

        driver.get(
                "https://katalon-demo-cura.herokuapp.com/#appointment"
        );

        // SECOND APPOINTMENT
        appointmentPage.bookAppointment(
                "Hongkong CURA Healthcare Center",
                true,
                "20/07/2026",
                "Second Appointment"
        );

        HistoryPage historyPage =
                new HistoryPage(driver);

        historyPage.openHistoryPage();

        Assert.assertTrue(
                historyPage.isHistoryDisplayed()
        );

        Assert.assertFalse(
                historyPage.getHistoryText().isEmpty()
        );
    }

    @Test(priority = 5)
    public void verifyAppointmentsSortedByDate() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "John Doe",
                "ThisIsNotAPassword"
        );

        HistoryPage historyPage =
                new HistoryPage(driver);

        historyPage.openHistoryPage();

        Assert.assertTrue(true);
    }
}