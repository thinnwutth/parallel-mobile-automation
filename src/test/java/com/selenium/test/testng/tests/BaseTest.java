package com.selenium.test.testng.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;

public class BaseTest {
    //public AppiumDriver driver;
    public ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    public void setDriver (AppiumDriver driver){
        this.driver.set(driver);
    }
    public AppiumDriver getDriver(){
        return this.driver.get();
    }
    public WebDriverWait wait;

    @Parameters({"deviceName","platformVersion","platformName","portNumber"})
    @BeforeMethod
    public void setUp(String deviceName, String platformVersion, String platformName, String portNumber) throws Exception {
        startAppiumService(portNumber);
        if(platformName.equalsIgnoreCase("iOS")){
            XCUITestOptions options = new XCUITestOptions();
            options.setDeviceName(deviceName)
                    .setPlatformVersion(platformVersion)
                    .setPlatformName("ios")
                    .setBundleId("com.apple.MobileAddressBook")
                    .setUdid("86E9F4B0-5799-46B5-9891-6365AA34449A")
                    .setNewCommandTimeout(Duration.ofSeconds(180));

            setDriver(new IOSDriver(new URL("http://0.0.0.0:"+portNumber+"/"), options));
        }
        else if(platformName.equalsIgnoreCase("Android")) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(deviceName)
                    .setPlatformVersion(platformVersion)
                    .setPlatformName(platformName)
                    .setAppPackage("com.android.contacts")
                    .setAppActivity("com.android.contacts.activities.PeopleActivity")
                    .setNewCommandTimeout(Duration.ofSeconds(180));

            setDriver(new AndroidDriver(new URL("http://0.0.0.0:"+portNumber+"/"), options));

        }
    }

    @AfterMethod
    public void tearDown() throws Exception {
        //getDriver().quit();
    }

    public void startAppiumService(String portNumber){
        AppiumDriverLocalService service;
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(Integer.parseInt(portNumber));
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        System.out.println("Service has been started with :" + portNumber);
    }
}
