package com.appium.practice;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseTest {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public UiAutomator2Options options;

    @BeforeClass
    public void ConfigureAppium() throws URISyntaxException, MalformedURLException {
        service = new AppiumServiceBuilder().
                withAppiumJS(new File("C:\\Users\\sayan\\AppData\\Roaming" +
                        "\\npm\\node_modules\\appium\\build\\lib\\main.js")).
                withIPAddress("127.0.0.1").
                usingPort(4723).
                build();

        service.start();
        options = new UiAutomator2Options();
        options.setDeviceName("TestPhone");
        options.setApp("D:\\APKFiles\\resources\\ApiDemos-debug.apk");

        driver = new AndroidDriver(
                new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        service.stop();
    }
}
