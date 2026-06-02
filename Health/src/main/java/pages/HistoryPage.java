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

    private By menuToggle =
            By.id("menu-toggle");

    private By historyLink =
            By.xpath("//nav//a[@href='history.php#history']");

    private By historyItems =
            By.tagName("h2");

    public void openHistoryPage() {

        // Open sidebar menu
        click(menuToggle);

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

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