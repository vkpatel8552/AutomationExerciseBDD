package test.AutomationExerciseBDD.TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/features/MenFeature"}, 
		glue = { "test.AutomationExerciseBDD.StepDefination",
				"test.AutomationExerciseBDD.AppHooks" },
		plugin = { "pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/" })

public class testRunner {

}