package com.appium.practice;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppiumBasics extends BaseTest {
    @Test
    public void WiFiSettingsName() {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();
        String alert_title = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alert_title, "WiFi settings");
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("TestWiFi");
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
    }
}
