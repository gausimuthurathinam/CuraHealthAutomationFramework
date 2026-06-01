package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AppointmentPage extends BasePage {
    public AppointmentPage(WebDriver driver) {
        super(driver);
    }

    private By facilityDropdown = By.id("combo_facility");
    private By admissionCheckBox = By.id("chk_hospotal_readmission") ;
    private By visitDate= By.id("txt_visit_date");
    private By commentField= By.id("txt_comment");
    private By bookAppointmentButton= By.id("btn-book-appointment");

    public void selectFacility(String facility) {

        Select select = new Select(waitForElement(facilityDropdown));
        select.selectByVisibleText(facility);
    }
    public void clickAdmissionCheckbox(){
        click(admissionCheckBox);
    }
    public void enterVisitDate(String date)
    {
        type(visitDate,date);
    }
    public void enterCommit(String comment)
    {
        type(commentField,comment);
    }
    public void clickBookAppointment() {
        click(bookAppointmentButton);
    }
    public void bookAppointment(
            String facility, boolean admission, String date, String comment)
    {
        selectFacility(facility);
        if(admission){
            clickAdmissionCheckbox();
        }
        enterVisitDate(date);
        enterCommit(comment);
        clickBookAppointment();
    }

}
