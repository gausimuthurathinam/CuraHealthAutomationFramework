package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AppointmentPage extends BasePage {

    public AppointmentPage(WebDriver driver) {
        super(driver);
    }

    private final By facilityDropdown = By.id("combo_facility");
    private final By admissionCheckBox = By.id("chk_hospotal_readmission");
    private final By visitDate = By.id("txt_visit_date");
    private final By commentField = By.id("txt_comment");
    private final By bookAppointmentButton = By.id("btn-book-appointment");

    // Validation locators
//    private final By dateRequiredMessage =By.xpath("//*[contains(text(),'required')]");

    public void selectFacility(String facility) {
        Select select = new Select(waitForElement(facilityDropdown));
        select.selectByVisibleText(facility);
    }


    public void clickAdmissionCheckbox() {

        waitForElement(admissionCheckBox).click();
    }

    public void enterVisitDate(String date) {

        type(visitDate, date);

        // CLOSE DATE PICKER
        waitForElement(visitDate).sendKeys("\t");
    }

    public void enterCommit(String comment) {
        type(commentField, comment);
    }

    public void clickBookAppointment() {
        click(bookAppointmentButton);
    }

    public void bookAppointment(
            String facility,
            boolean admission,
            String date,
            String comment) {

        selectFacility(facility);

        if (date != null && !date.isEmpty()) {

            enterVisitDate(date);
        }

        // CLICK CHECKBOX AFTER DATE PICKER CLOSES
        if (admission) {

            clickAdmissionCheckbox();
        }

        enterCommit(comment);

        clickBookAppointment();
    }

    // MODULE 5 METHODS

    public boolean isStillOnAppointmentPage() {
        return driver.getCurrentUrl().contains("#appointment");
    }

    public boolean isBookAppointmentButtonEnabled() {
        return waitForElement(bookAppointmentButton).isEnabled();
    }
}