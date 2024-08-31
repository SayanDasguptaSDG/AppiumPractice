package com.appium.practice;
import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class AppiumBasics extends BaseTest {
    @Test
    public void WiFiSettingsName() {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
    }
}
