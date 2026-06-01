package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {

        super(driver);

        this.driver = driver;
    }

    By makeAppointmentBtn =
            By.id("btn-make-appointment");

    By username =
            By.id("txt-username");

    By password =
            By.id("txt-password");

    By loginBtn =
            By.id("btn-login");

    By errorMessage =
            By.xpath("//p[@class='lead text-danger']");

    By menuToggle =
            By.id("menu-toggle");

    By logoutBtn =
            By.linkText("Logout");

    public void clickMakeAppointment() {

        click(makeAppointmentBtn);
    }

    public void login(String user, String pass) {

        type(username, user);

        type(password, pass);

        click(loginBtn);
    }

    public String getErrorMessage() {

        return getText(errorMessage);
    }

    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains("#appointment");
    }

    public void logout() {

        click(menuToggle);

        click(logoutBtn);
    }

    public boolean isLoginPageDisplayed() {

        return driver.findElement(
                By.id("btn-login")
        ).isDisplayed();
    }
}