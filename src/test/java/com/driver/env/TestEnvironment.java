package com.driver.env;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestEnvironment {

    private String env;

    protected final Log logger = LogFactory.getLog(getClassnameForLog());
    protected Properties prop = new Properties();
    private String baseURL;
    private String envUser;
    private String password;
    private String pathFiles;

    public TestEnvironment() {
        this.setUP();
    }

    protected String getClassnameForLog() {
        return this.getClass().getName();
    }

    private void setUP() {

        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("insight-gui-test.properties");
            prop.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        env = prop.getProperty("environment");
        baseURL = prop.getProperty("baseurl");

        if (env.equals("local")) {
            try {
                envUser = prop.getProperty("username");
                password = prop.getProperty("password");
                pathFiles = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
                // driver.get(baseURL);
            } catch (Exception ee) {
                logger.error("Could not prepare test data: ", ee);
                assertTrue(false);
            }

        } else if (env.equals("remote")) {
            try {
                envUser = prop.getProperty("username");
                password = prop.getProperty("password");
            } catch (Exception ee) {
                logger.error("Could not prepare test data: ", ee);
                assertTrue(false);
            }
        } else {
            logger.info("Provide valid environment parameter: local or remote");
        }
    }

    public String getEnvUser() {
        return envUser;
    }

    public String getPassword() {
        return password;
    }

    public String getPathFiles() {
        return pathFiles;
    }

    public String getBaseURL() {
        return baseURL;
    }
}
