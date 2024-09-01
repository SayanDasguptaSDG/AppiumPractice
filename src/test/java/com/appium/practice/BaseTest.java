package com.appium.practice;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import jdk.jfr.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
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

    @Description("Long Press Gesture")
    public void longPressAction(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile:longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                        "duration", 2000));
    }

    @Description("Scroll Down Till End")
    public void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).
                    executeScript("mobile: scrollGesture", ImmutableMap.of(
                            "left", 100, "top", 100,
                            "width", 200, "height", 200,
                            "direction", "down",
                            "percent", 1.0
                    ));
        } while(canScrollMore);
    }

    @Description("Swipe Gesture")
    public void swipeAction(WebElement element, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile:swipeGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                        "direction", direction, "percent", 0.75));
    }

    public void dragAndDropAction(WebElement element, int x, int y) {
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", x,
                "endY", y
        ));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        service.stop();
    }
}
