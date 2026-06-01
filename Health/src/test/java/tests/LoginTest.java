package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExcelUtils;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getExcelData() throws IOException
    {
        String path = "src/test/resources/LoginData.xlsx";
        return ExcelUtils.getExcelData(path, "Sheet1");
    }

    @Test
    public void verifyValidLogin() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.clickMakeAppointment();

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login failed - user not redirected to appointment page"
        );
    }

    @Test
    public void verifyInvalidLogin() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.clickMakeAppointment();

        loginPage.login(
                "wrongUser",
                "wrongPass"
        );

        String actual =
                loginPage.getErrorMessage();

        Assert.assertTrue(
                actual.contains("Login failed")
        );
    }

    @Test
    public void verifyLogout() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.clickMakeAppointment();

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        loginPage.logout();

        String currentUrl =
                driver.getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains(
                        ConfigReader.getProperty("baseUrl")
                )
        );
    }

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void verifyProtectedPageRedirect() {

        driver.get(
                ConfigReader.getProperty("baseUrl")
                        + "/profile.php#appointment"
        );

        LoginPage loginPage =
                new LoginPage(driver);

        boolean status =
                loginPage.isLoginPageDisplayed();

        Assert.assertTrue(status);
    }
}