package com.selenium.test.testng.tests;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import com.selenium.test.testng.tests.androidAppEvents;

public class androidContactApp extends BaseTest {
    @Test
    public void androidExecutionMethod() throws Exception {
        androidAppEvents androidAppEvents_obj = new androidAppEvents(getDriver());
        Thread.sleep(5000);
        androidAppEvents_obj.login();
    }

}