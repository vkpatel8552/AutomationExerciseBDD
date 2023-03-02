package test.AutomationExerciseBDD.TestRunner;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import test.automationframework.Utils.Efficacies;
import test.automationframework.Utils.EmailTestExecutionReports;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/features"}, 
		glue = { "test.AutomationExerciseBDD.StepDefination",
				"test.AutomationExerciseBDD.AppHooks" },
		plugin = { "pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/" })

public class testRunner {
	public static void main(String[] args) throws Exception {
		Properties emailProperty = new Efficacies().loadPropertyFile(args[0]);
		EmailTestExecutionReports email = new EmailTestExecutionReports(emailProperty);
		Session session = email.setBasicEmailConfiguration()
							   .createNewEmailSession();
		Message msg = email.setEmailMsgContent(session);
		email.sendEmail(msg, emailProperty.getProperty("pdfReport"));
	}

}