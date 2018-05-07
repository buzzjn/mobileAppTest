package com.driver.config;

import com.driver.env.TestEnvironment;
import com.google.inject.Inject;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverConfig {
    private final TestEnvironment environment;

    @Inject
    public DriverConfig(final TestEnvironment environment) {
        this.environment = environment;
    }

    public IOSDriver getIosDriver() {
        URL localUrl = null;

        final File iosAppDir = new File(environment.getPathFiles()+"");
        final File iosApp = new File(iosAppDir, "Hive.app");

        try {
            localUrl = new URL(environment.getBaseURL());
        } catch (MalformedURLException e) {
            // do nothing
        }

        final DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.4");
        // capabilities.setCapability(MobileCapabilityType.APP_WAIT_ACTIVITY, "true");
        capabilities.setCapability(MobileCapabilityType.APP, iosApp);
        capabilities.setCapability("waitForAppScript", "true");
        return new IOSDriver(localUrl, capabilities);
    }
}
