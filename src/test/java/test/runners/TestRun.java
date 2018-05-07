package test.runners;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = "pretty", features = "src/test/resources/features/", tags = "@iosapp", glue = "test.java.test.steps")

public class TestRun {

}
