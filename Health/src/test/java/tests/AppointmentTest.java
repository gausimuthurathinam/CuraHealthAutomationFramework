package tests;
import base.BaseTest;
import net.bytebuddy.build.Plugin;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AppointmentPage;
import pages.ConfirmationPage;
import pages.LoginPage;

public class AppointmentTest extends BaseTest {

    @Test(priority=1)
    public void verifyAppointmentBooking() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("John Doe", "ThisIsNotAPassword");
        AppointmentPage appointmentPage = new AppointmentPage(driver);

        appointmentPage.bookAppointment("Tokyo CURA Healthcare Center", false, "20/07/2026", "General Checkup");
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        Assert.assertEquals(confirmationPage.getFacility(), "Tokyo CURA Healthcare Center");
        Assert.assertEquals(confirmationPage.getVisitDate(), "20/07/2026");
    }
    @Test(priority = 2)
    public void verifyAdmissionBooking() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("John Doe", "ThisIsNotAPassword");
        AppointmentPage appointmentPage = new AppointmentPage(driver);
        appointmentPage.bookAppointment("Hongkong CURA Healthcare Center", true, "20/07/2026", "Admission Test");

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        Assert.assertEquals(confirmationPage.getAdmissionStatus(), "Yes");
    }
    @Test(priority = 3)
    public void verifyPastDateValidation() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                "John Doe",
                "ThisIsNotAPassword"
        );

        AppointmentPage appointmentPage =
                new AppointmentPage(driver);

        appointmentPage.bookAppointment(
                "Tokyo CURA Healthcare Center",
                false,
                "01/01/2020",
                "Past Date Test"
        );

        String currentUrl =
                driver.getCurrentUrl();

        Assert.assertFalse(
                currentUrl.contains("summary"),
                "Past date should not be accepted"
        );
    }

}