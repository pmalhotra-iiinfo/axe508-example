package gov.uscis.odds.runner;

import org.junit.runner.RunWith;

import com.karsun.kic.tan.duke.annotations.TestDataFiles;
import com.karsun.kic.tan.duke.cukes.MergedDataInjectedCucumber;

import cucumber.api.CucumberOptions;

@RunWith(MergedDataInjectedCucumber.class)
@CucumberOptions(plugin = { "json:build/calories-tracker.json", "html:build/calories-tracker" },
		tags = {"~@wip"},
		features = {
		"src/test/resources/features/"},
		glue={"com.karsun.kic.tan", "org.openqa", "gov.uscis.odds"})
@TestDataFiles(files = { "src/test/resources/data/data.json" })
public class CaloriesTrackerRunner {
}