import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class InstallAppFromPlayStore {

    private AppiumDriver driver;
    private WebDriverWait wait;
    final String AppName = "TestApp.io";
    final String installedAppPackage = "testapp.io";
    final String installedAppActivity = "com.testappio.MainActivity";
    final String userName = "thinwutt.hmone.mhs@gmail.com";
    final String password = "My@nm@r2023";

    //Elements of PlayStore
    String PlayStoreClickSearchBox = "//android.widget.TextView[@text='Search apps & games']";
    String PlayStoreEditSearchTextBox = "android.widget.EditText";
    String PlayStoreTextView = "//android.widget.TextView[@text='" + AppName + "']";
    String PlayStoreAppInstall = "//android.widget.Button[@text='Install']";
    String PlayStoreAppUnintall = "//android.widget.Button[@text='Uninstall']";

    //Elements of TestApp.io for Android
    String TestAppSkip = "//android.widget.TextView[@text='skip']";
    String TestAppEmail = "//android.widget.EditText[@text='Email Address']";
    String TestAppPassword = "//android.widget.EditText[@text='Enter your password']";
    String TestAppSignIn = "//android.widget.TextView[@text='Sign In']";
    String TestAppBurgerMenu = "(//android.widget.TextView)[1]";
    String TestAppAccountSubMenu = "//android.view.ViewGroup[@resource-id='ACCOUNT_AND_SETTINGS_DRAWER_ITEM']";
    String TestAppSignOut = "//android.widget.TextView[@text='Sign Out']";

    @Before
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel-7")
                .setPlatformName("Android")
                .setAppPackage("com.android.vending")
                .setAppActivity("com.google.android.finsky.activities.MainActivity")
                .setNewCommandTimeout(Duration.ofSeconds(180));

        this.driver = new AndroidDriver(new URL("http://0.0.0.0:4723/"), options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }
    @After
    public void tearDown() throws Exception {
        //driver.quit();
    }

    @Test
    public void installAppFromGooglePlayStore() throws Exception {
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(PlayStoreClickSearchBox))))
                .click();

        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.className(PlayStoreEditSearchTextBox))))
                .sendKeys(AppName);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(PlayStoreTextView))))
                .click();

        Thread.sleep(5000);
        new WebDriverWait(driver, Duration.ofSeconds(800)).until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(PlayStoreAppUnintall)))
                .click();

        System.out.println("Installed " + AppName);
        driver.quit();

        this.driver = new AndroidDriver(new URL("http://0.0.0.0:4723/"), installedTestAppCap());

        Thread.sleep(5000);
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(TestAppSkip)))
                .click();

        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(TestAppEmail))))
                .sendKeys(userName);

        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(TestAppPassword))))
                .sendKeys(password);

        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(TestAppSignIn))))
                .click();

        Thread.sleep(5000);
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(TestAppBurgerMenu)))
                .click();

        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(TestAppAccountSubMenu))))
                .click();
        Thread.sleep(5000);

        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(TestAppSignOut))))
                .click();

        System.out.println("Successful Logout!");

        ((AndroidDriver) driver).removeApp(installedAppPackage);
        System.out.println("Unstalled "+ AppName);
    }
    private UiAutomator2Options installedTestAppCap() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel-7")
                .setPlatformName("Android")
                .setAppPackage(installedAppPackage)
                .setAppActivity(installedAppActivity)
                .setNewCommandTimeout(Duration.ofSeconds(180));

        return options;
    }
}