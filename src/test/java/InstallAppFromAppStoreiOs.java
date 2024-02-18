import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.windows.WindowsElement;
import javafx.scene.input.TouchEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;


public class InstallAppFromAppStoreiOs {

    private AppiumDriver driver;
    private WebDriverWait wait;
    final String AppName = "SnapChat";
    final String installedAppPackage = "com.toyopagroup.picaboo";
    String userName = "sushant233646";
    String password = "myanmars";

    //Elements of AppStore
    String AppStoreSearchBox = "UIA.AppStore.TabBar.search";
    String AppStoreSearchTextField = "UIA.AppStore.SearchTextField";
    String AppStoreTextView = "Snapchat, Share the moment";
    String AppStoreButton = "//XCUIElementTypeButton[@name='UIA.AppStore.OfferButton' and (@label='Get' or @label='re-download')]";
    String AppStoreOpenButton = "//XCUIElementTypeButton[@name='UIA.AppStore.OfferButton' and @label='OPEN']";


    //Elements of SNapchat
    String TestAppLogin = "LOG IN";
    String TestAppEmailiOs = "username";
    String TestAppPasswordiOs = "PASSWORD";
    String TestAppLoginButton = "(//XCUIElementTypeStaticText[@name='Log In'])[2]";
    String TestAppAccessNotAllow = "Don’t Allow";
    String TestAppAccessOK= "OK";
    String TestAppProfile = "profile";
    String TestAppSetting = "//XCUIElementTypeOther[@name='unified_profile_icon_button_settings']/XCUIElementTypeImage";
    String TestAppLogout = "Log Out";
    @Before
    public void setUp() throws Exception {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhoneX")
                .setPlatformVersion("16.6.1")
                .setPlatformName("ios")
                .setBundleId("com.apple.AppStore")
                .setUdid("3627fc75276cdbbe7d1055f8743fb4ca2adb8acd")
                .setNewCommandTimeout(Duration.ofSeconds(180));

        this.driver = new IOSDriver(new URL("http://0.0.0.0:4723/")
                , options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    @After
    public void tearDown() throws Exception {
        //driver.quit();
    }

    @Test
    public void installAppFromAppStore() throws Exception {
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(AppStoreSearchBox))))
                .click();

        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(AppStoreSearchTextField))))
                .click();

        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(AppStoreSearchTextField))))
                .sendKeys(AppName);

        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND label == 'search'")).click();

        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(AppStoreTextView))))
                .click();

        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(AppStoreButton))))
                .click();

        Thread.sleep(5000);
        new WebDriverWait(driver, Duration.ofSeconds(800)).until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(AppStoreOpenButton)));

        System.out.println("Installed " + AppName);
        driver.quit();

        this.driver = new IOSDriver(new URL("http://0.0.0.0:4723/"), installedTestAppCap());

        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(TestAppLogin))))
                .click();


        Thread.sleep(5000);
        WebElement email = driver.findElement(MobileBy.AccessibilityId(TestAppEmailiOs));
        wait.until(ExpectedConditions.elementToBeClickable(email));
        email.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(email, userName).perform();

        Thread.sleep(2000);
        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND label == 'next'")).click();
        Thread.sleep(2000);
        WebElement passwordField = driver.findElement(MobileBy.AccessibilityId(TestAppPasswordiOs));
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.click();
        actions.sendKeys(passwordField, password).perform();

        Thread.sleep(2000);
        //driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND label == 'next'")).click();
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(By.xpath(TestAppLoginButton))))
                .click();

        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(TestAppAccessNotAllow))))
                .click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(TestAppAccessOK))))
                .click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(TestAppAccessNotAllow))))
                .click();

        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(TestAppProfile))))
                .click();

        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(By.xpath(TestAppSetting))))
                .click();

        Thread.sleep(3000);
        scroll(driver,0.25,1,0.25,0.0);
        scroll(driver,0.25,1,0.25,0.0);
        scroll(driver,0.25,1,0.25,0.0);
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOf(
                        driver.findElement(MobileBy.AccessibilityId(TestAppLogout))))
                .click();

        ((IOSDriver) driver).removeApp(installedAppPackage);
        System.out.println("Uninstalled "+ AppName);

    }
    private XCUITestOptions installedTestAppCap() throws Exception {

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhoneX")
                .setPlatformVersion("16.6.1")
                .setPlatformName("ios")
                .setBundleId(installedAppPackage)
                .setUdid("3627fc75276cdbbe7d1055f8743fb4ca2adb8acd")
                .setNewCommandTimeout(Duration.ofSeconds(180));

        return options;
    }
    public void scroll(AppiumDriver driver, double start_xd, double start_yd, double end_xd, double end_yd) throws InterruptedException {
        Dimension dimension = driver.manage().window().getSize();
        int start_x = (int) (dimension.width * start_xd);
        int start_y = (int) (dimension.height * start_yd);

        int end_x = (int) (dimension.width * end_xd);
        int end_y = (int) (dimension.height * end_yd);

        TouchAction touchAction = new TouchAction ((PerformsTouchActions) driver);
        touchAction.press(PointOption.point(start_x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(end_x, end_y)).release().perform();

        Thread.sleep(3000);
    }

}