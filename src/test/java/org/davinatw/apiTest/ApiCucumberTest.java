package org.davinatw.apiTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"org.davinatw.apiTest"},
        features = {"src/test/resources/API"},
        plugin = {"pretty","html:reports/api-report.html", "json:reports/api-report.json"}
)

public class ApiCucumberTest {
}
