package com.selenium.test.webtestsbase;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Sidelnikov Mikhail on 18.09.14.
 * Class presents functionality for generation of {@link org.openqa.selenium.remote.DesiredCapabilities} object
 * need for some browsers start
 */
public class CapabilitiesGenerator {

    /**
     * getting {@link org.openqa.selenium.remote.DesiredCapabilities} object based on browser
     * ATTENTION: you should specify the path to chrome driver executable file to run tests on it(@see <a href="https://sites.google.com/a/chromium.org/chromedriver/getting-started">here for more info</a>)
     * @param browser {@link com.selenium.test.webtestsbase.Browser} object
     * @return capabilites needed for some browsers start
     */
    public static DesiredCapabilities getDefaultCapabilities(Browser browser) {
        switch (browser) {
            case CHROME:
                if (System.getProperty("webdriver.chrome.driver") == null) {
                    throw new IllegalStateException("System variable 'webdriver.chrome.driver' should be set to the path for the Chrome driver executable");
                }
                ChromeOptions chromeOptions = new ChromeOptions();
                // Add Chrome-specific options if needed
                //return chromeOptions.merge(DesiredCapabilities.chrome());
            default:
                throw new IllegalStateException("Browser is not supported");
        }
    }

}
