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
    public Object[][] getExcelData() throws IOException {

        String path = "src/test/resources/LoginData.xlsx";

        return ExcelUtils.getExcelData(
                path,
                "Sheet1"
        );
    }

    // TEST CASE 1
    @Test(
            priority = 1,
            dataProvider = "loginData"
    )
    public void verifyLogin(
            String username,
            String password,
            String expectedResult
    ) {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.clickMakeAppointment();

        loginPage.login(
                username,
                password
        );

        if(expectedResult.equalsIgnoreCase("Valid"))
        {
            Assert.assertTrue(
                    loginPage.isLoginSuccessful(),
                    "Valid login failed"
            );
        }
        else if(expectedResult.equalsIgnoreCase("Invalid"))
        {
            Assert.assertTrue(
                    loginPage.getErrorMessage()
                            .contains("Login failed"),
                    "Invalid login error message not displayed"
            );
        }
    }

    // TEST CASE 2
    @Test(priority = 2, retryAnalyzer = utils.RetryAnalyzer.class)
    public void verifyLogout() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.clickMakeAppointment();

        loginPage.login(
                "John Doe",
                "ThisIsNotAPassword"
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

    // TEST CASE 3
    @Test(priority = 3)
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