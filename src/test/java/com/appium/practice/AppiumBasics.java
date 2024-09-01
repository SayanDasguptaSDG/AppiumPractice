package com.appium.practice;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebElement;
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

    @Test
    public void LongPressGesture() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"1. Custom Adapter\"]")).click();
        WebElement element = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"People Names\"]"));
        longPressAction(element);
        String menu_text = driver.findElement(AppiumBy.id("android:id/title")).getText();
        Assert.assertEquals(menu_text, "Sample menu");
        Assert.assertTrue(driver.findElement(AppiumBy.id("android:id/title")).isDisplayed());
    }

    @Test
    public void ScrollDown() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        //when destination is known beforehand -
        // use Google engine provided Android UiAutomator action
        driver.findElement(AppiumBy.androidUIAutomator
                ("new UiScrollable(new UiSelector())." +
                        "scrollIntoView(text(\"WebView\"));"));

        //when there is no prior destination, scroll till end - Appium action
        scrollToEndAction();
        Thread.sleep(2000);
    }

    @Test
    public void swipeLeft() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Gallery\"]")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        WebElement first_image = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));
        Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "true");
        swipeAction(first_image, "left");
        Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "false");
    }

    @Test
    public void dragAndDrop() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement itemTobeDraggedAndDropped = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
        dragAndDropAction(itemTobeDraggedAndDropped, 619, 560);
        String result = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(result, "Dropped!");
    }

    @Test
    public void WiFiSettingsNameInLandscapeMode() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        DeviceRotation deviceRotation = new DeviceRotation(0, 0, 90);
        driver.rotate(deviceRotation);
        Thread.sleep(2000);

        driver.findElement(AppiumBy.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();
        String alert_title = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alert_title, "WiFi settings");
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("TestWiFi");
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
    }

    @Test
    public void copyAndPasteFromClipboard() {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        driver.findElement(AppiumBy.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();
        String alert_title = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alert_title, "WiFi settings");
        driver.setClipboardText("JOHN DOE");
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
    }

    @Test
    public void keyEvents() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        driver.findElement(AppiumBy.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();
        String alert_title = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alert_title, "WiFi settings");
        driver.setClipboardText("JOHN DOE");
        driver.findElement(AppiumBy.id("android:id/edit")).click();
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        Thread.sleep(2000);
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
