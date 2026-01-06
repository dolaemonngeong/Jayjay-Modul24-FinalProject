package org.davinatw.WebUITest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"org.davinatw.WebUITest"},
        features = {"src/test/resources/Web"},
        plugin = {"pretty","html:reports/web-report.html", "json:reports/web-report.json"}
)

public class CucumberTest {
}
