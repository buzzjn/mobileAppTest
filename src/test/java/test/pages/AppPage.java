package test.pages;

import org.openqa.selenium.By;

import com.google.inject.Inject;

import io.appium.java_client.ios.IOSDriver;

public class AppPage extends AppPageObject {

    @Inject
    public AppPage(final IOSDriver iosDriver) {
        super(iosDriver);
    }

    public void swipeLeft() {
        iosDriver.swipe(600, 300, 80, 300, 1000);
        try {
            iosDriver.wait(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swipeRight() {
        iosDriver.swipe(80, 300, 600, 300, 1000);
        try {
            iosDriver.wait(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        iosDriver.findElement(By.id("refresh_button")).click();
        waitForPageLoad();
    }

    public boolean onPage(final String page) {
        final String title = iosDriver.findElement(By.id("title")).getText();
        return title.equals(page);
    }
}
