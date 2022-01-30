package CodilityTest.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(monochrome = true, features = "src/test/resource/CodilityTest/features/f1.feature", glue = {
		"CodilityTest.stepdefs", "CodilityTest.hooks" }, plugin = { "pretty",
				"html:target/cucumber-report/cucumber-report.html",
				"json:target/cucumber-report/cucumber-report.json" })
public class TestRunner extends AbstractTestNGCucumberTests {

}