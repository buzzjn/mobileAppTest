package test.pages;

import org.openqa.selenium.By;

import com.google.inject.Inject;

import io.appium.java_client.ios.IOSDriver;

public class AppPageObject {

    protected final IOSDriver iosDriver;

    protected static final long DEFAULT_TIMEOUT_IN_SECONDS = Long.getLong("webdriver.timeout", 300);

    @Inject
    public AppPageObject(final IOSDriver iosDriver) {
        this.iosDriver = iosDriver;
    }

    public void waitForPageLoad() {
        final long startTimeInMillis = System.currentTimeMillis();
        boolean hasTimedOut = false;
        while (inProgress() && !hasTimedOut) {
            try {
                iosDriver.wait(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hasTimedOut = System.currentTimeMillis() - startTimeInMillis >= (DEFAULT_TIMEOUT_IN_SECONDS * 1000);
        }
    }

    private boolean inProgress() {
        try {
            iosDriver.findElement(By.id("Hello!"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
