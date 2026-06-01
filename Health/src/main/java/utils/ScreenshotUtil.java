package utils;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;

public class ScreenshotUtil {

    public static void captureScreenshot(
            WebDriver driver,
            String testName) {

        String timestamp =
                new SimpleDateFormat(
                        "yyyyMMddHHmmss")
                        .format(new Date());

        String path =
                "screenshots/" +
                        testName +
                        "_" +
                        timestamp +
                        ".png";

        File src =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(
                                OutputType.FILE);

        try {

            FileUtils.copyFile(
                    src,
                    new File(path));

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to save screenshot", e);
        }
    }
}