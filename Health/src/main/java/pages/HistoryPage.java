package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class HistoryPage extends BasePage {

    public HistoryPage(WebDriver driver) {
        super(driver);
    }

    private final By menuToggle =
            By.id("menu-toggle");

    private final By historyLink =
            By.xpath("//nav//a[@href='history.php#history']");

    private final By historyItems =
            By.tagName("h2");


    private final By historyHeader =
            By.xpath("//h2");

    public boolean isHistoryDisplayed() {

        return isDisplayed(historyHeader);
    }


    public void openHistoryPage() {

        // Open sidebar menu
        click(menuToggle);


        // Click using JavaScript
        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].click();",
                driver.findElement(historyLink)
        );
    }

    public List<String> getHistoryText() {

        List<String> history =
                new ArrayList<>();

        driver.findElements(historyItems)
                .forEach(element ->
                        history.add(element.getText()));

        return history;
    }


}