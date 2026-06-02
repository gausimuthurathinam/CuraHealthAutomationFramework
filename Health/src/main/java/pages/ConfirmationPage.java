package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends BasePage {

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }
    private By facilityText = By.id("facility");
    private By admissionText = By.id("hospital_readmission");
    private By visitDateText = By.id("visit_date");
    public String getFacility() {
        return getText(facilityText);
    }
    public String getAdmissionStatus() {
        return getText(admissionText);
    }
    public String getVisitDate() {
        return getText(visitDateText);
    }
}