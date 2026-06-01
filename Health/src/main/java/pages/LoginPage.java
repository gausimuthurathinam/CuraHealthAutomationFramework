package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

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

    private By menuToggle =
            By.id("menu-toggle");

    private By logoutBtn =
            By.linkText("Logout");

    /**
     * Navigate to Login Page
     */
    public void clickMakeAppointment() {
        click(makeAppointmentBtn);
    }

    /**
     * Complete Login Flow
     */
    public void login(String username,
                      String password) {

        clickMakeAppointment();

        type(usernameField, username);

        type(passwordField, password);

        click(loginBtn);
    }

    /**
     * Invalid Login Error
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Verify Login Success
     */
    public boolean isLoginSuccessful() {

        return driver.getCurrentUrl()
                .contains("#appointment");
    }

    /**
     * Logout
     */
    public void logout() {

        click(menuToggle);

        click(logoutBtn);
    }

    /**
     * Verify Login Page Displayed
     */
    public boolean isLoginPageDisplayed() {

        return waitForElement(loginBtn)
                .isDisplayed();
    }
}