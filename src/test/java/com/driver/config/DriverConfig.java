package com.driver.config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.driver.env.TestEnvironment;
import com.google.inject.Inject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverConfig {
    private final TestEnvironment environment;

    @Inject
    public DriverConfig(final TestEnvironment environment) {
        this.environment = environment;
    }

    public IOSDriver getIosDriver() {
        URL localUrl = null;

        final File iosAppDir = new File(environment.getPathFiles() + "");
        final File iosApp = new File(iosAppDir, "Calendar.app");

        try {
            localUrl = new URL(environment.getBaseURL());
        } catch (MalformedURLException e) {
            // do nothing
        }

        final DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.3");
        capabilities.setCapability(MobileCapabilityType.APP, iosApp);
        capabilities.setCapability("waitForAppScript", "true");
        return new IOSDriver(localUrl, capabilities);
    }
}
