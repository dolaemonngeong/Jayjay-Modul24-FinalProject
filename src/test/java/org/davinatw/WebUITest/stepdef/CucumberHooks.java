package org.davinatw.WebUITest.stepdef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.davinatw.WebUITest.BaseTest;

public class CucumberHooks extends BaseTest {

    @Before
    public void beforeTest(){
        getDriver();
    }

    @After
    public void afterTest(){

        driver.close();
    }
}
