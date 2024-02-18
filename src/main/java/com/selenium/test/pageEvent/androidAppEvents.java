package com.selenium.test.testng.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class androidAppEvents{

    AppiumDriver driver;
    public androidAppEvents(AppiumDriver driver){
        this.driver=driver;
    }
    public WebDriverWait wait;
    //locators
    String addButton = "Create new contact";
    String firstName = "//android.widget.EditText[@text='First name']";
    String lastName = "//android.widget.EditText[@text='Last name']";
    String enterPhoneNumber = "//android.widget.EditText[@text='Phone']";
    String saveButton = "com.android.contacts:id/editor_menu_save_button";

    //input value
    String firstNameValue = "Thin Wutt Hmone";
    String lastNameValue = "@Isabella Lady";
    String phoneNumberValue = "09787888888";

    public void login() throws Exception {

        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                        MobileBy.AccessibilityId(addButton)))
                .click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(By.xpath(firstName))))
                .sendKeys(firstNameValue);

        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(By.xpath(lastName))))
                .sendKeys(lastNameValue);

        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(By.xpath(enterPhoneNumber))))
                .sendKeys(phoneNumberValue);

        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.id(saveButton)))
                .click();

        System.out.println("Successful added!");

    }

}