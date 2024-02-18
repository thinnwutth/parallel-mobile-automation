package com.selenium.test.testng.tests;

import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;


public class iosAppEvent{
     AppiumDriver driver;
    public WebDriverWait wait;
    public iosAppEvent(AppiumDriver driver){
        this.driver=driver;
    }

    //locators
    String addButton = "Add";
    String firstName = "First name";
    String lastName = "Last name";
    String company = "Company";
    String addPhoneButton = "Insert add phone";
    String enterPhoneNumber = "name == 'mobile' AND value == 'Phone'";
    String doneButton = "Done";

    //input value
    String firstNameValue = "Thin Wutt Hmone";
    String lastNameValue = "@Isabella Lady";
    String companyValue = "Space X Co.ltd";
    String phoneNumberValue = "09787888888";
    public void contactBook() throws Exception {
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        //Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(addButton))))
                .click();

        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(firstName))))
                .sendKeys(firstNameValue);

        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(lastName))))
                .sendKeys(lastNameValue);

        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(company))))
                .sendKeys(companyValue);

        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(addPhoneButton))))
                .click();

        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(AppiumBy.iOSNsPredicateString(enterPhoneNumber))))
                .sendKeys(phoneNumberValue);

        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.accessibilityId(doneButton))))
                .click();

    }

}