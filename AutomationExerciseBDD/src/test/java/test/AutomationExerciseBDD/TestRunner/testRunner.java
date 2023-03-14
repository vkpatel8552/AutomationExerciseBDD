package test.AutomationExerciseBDD.TestRunner;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import test.automationframework.Notification.EmailTestExecutionReports;
import test.automationframework.Utils.Efficacies;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/features"}, 
		glue = { "test.AutomationExerciseBDD.StepDefination",
				"test.AutomationExerciseBDD.AppHooks" },
		plugin = { "pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/",
				"rerun:target/failedrerun.txt"})

public class testRunner {
	public static void main(String[] args) throws Exception {
		
		// Email sent logic. Main method called using plugins added under pom.xml 
		Properties emailProperty = new Efficacies().loadPropertyFile(args[0]);
		EmailTestExecutionReports email = new EmailTestExecutionReports(emailProperty);
		Session session = email.setBasicEmailConfiguration()
							   .createNewEmailSession();
		Message msg = email.setEmailMsgContent(session);
		email.sendEmail(msg, emailProperty.getProperty("executionReport"));
	}

}