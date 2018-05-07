package com.driver.provider;

import org.junit.Before;
import org.openqa.selenium.remote.UnreachableBrowserException;

import com.driver.config.DriverConfig;
import com.google.inject.Inject;
import com.google.inject.Provider;

import io.appium.java_client.ios.IOSDriver;

public class IOSDriverProvider implements Provider<IOSDriver> {
    private static volatile IOSDriver iosDriver;
    private static boolean iosDriverNotRunning = true;

    @Inject
    private DriverConfig driverConfig;

    @Before
    public static void beforeAll() {
        if (iosDriverNotRunning) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    if (iosDriver != null) {
                        try {
                            iosDriver.quit();
                        } catch (UnreachableBrowserException e) {
                        }

                    }
                }
            });
            iosDriverNotRunning = false;
        }
    }

    @Override
    public IOSDriver get() {
        synchronized (IOSDriverProvider.class) {
            if (iosDriver == null) {
                final IOSDriver iosDriver = driverConfig.getIosDriver();
                IOSDriverProvider.iosDriver = iosDriver;
            }
        }
        return iosDriver;
    }
}
