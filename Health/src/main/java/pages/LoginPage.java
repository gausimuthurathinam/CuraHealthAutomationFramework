package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators

    private By makeAppointmentBtn =
            By.id("btn-make-appointment");

    private By usernameField =
            By.id("txt-username");

    private By passwordField =
            By.id("txt-password");

    private By loginBtn =
            By.id("btn-login");

    private By errorMessage =
            By.xpath("//p[@class='lead text-danger']");

    // Actions

    public void clickMakeAppointment() {
        click(makeAppointmentBtn);
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLogin() {
        click(loginBtn);
    }

    // Login Method

    public void login(String username, String password) {

        clickMakeAppointment();

        enterUsername(username);

        enterPassword(password);

        clickLogin();
    }

    // Verify Successful Login

    public boolean isLoginSuccessful() {

        return driver.getCurrentUrl().contains("#appointment");
    }

    // Error Message

    public String getErrorMessage() {

        return getText(errorMessage);
    }

    // MODULE 5 METHODS

    public void clickLoginOnly() {

        clickMakeAppointment();

        click(loginBtn);
    }

    public String getUsernameValidationMessage() {

        return waitForElement(usernameField)
                .getAttribute("validationMessage");
    }

    public String getPasswordValidationMessage() {

        return waitForElement(passwordField)
                .getAttribute("validationMessage");
    }
}