package com.appium.practice;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class AppiumBasics {
    @Test
    public void AppiumTest() throws MalformedURLException, URISyntaxException {
        AppiumDriverLocalService service = new AppiumServiceBuilder().
                withAppiumJS(new File("C:\\Users\\sayan\\AppData\\Roaming" +
                        "\\npm\\node_modules\\appium\\build\\lib\\main.js")).
                withIPAddress("127.0.0.1").
                usingPort(4723).
                build();

        service.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("TestPhone");
        options.setApp("D:\\APKFiles\\resources\\ApiDemos-debug.apk");

        AndroidDriver driver = new AndroidDriver(
                new URI("http://127.0.0.1:4723").toURL(), options);

        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.quit();
        service.stop();
    }

}
