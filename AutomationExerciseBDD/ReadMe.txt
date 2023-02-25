How to run individual Feature file?

>> Go to "AutomationExerciseBDD/src/test/java/test/AutomationExerciseBDD/TestRunner/testRunner.java"
>> Update features = { "src/test/features"} as { "src/test/features/<Whatever Features you want to run>"}
>> Right click on testrunner.java file and select option Run as >> Junit

How to run all feature file sequentially? 
>> Right click on testrunner.java file and select option Run as >> Junit

How to run all features in parallel?
>> Select AutomationExerciseBDD project 
>> Right click
>> Select Option as Run as >> Run Configuration >> Add New Maven Build Configuration
>> Give configuration name
>> Select workspace directory as given project directory
>> Enter maven goal as "clean verify"
>> Click on Run

This will run all feature files in parallel based on parallel thread count given under pom file for 'maven-failsafe-plugin'

================================ Email Configuration Settings ========================
When we run the automation suite with Maven configuration, it will also send notification email with test automation report

If user wants to send email after test execution below proeprties need to be updated under 

Step1:
src>> main >> emailConfig.properties file

smtpServer=<Which ever smtp server you want to use>
senderEmail=<your email address>
senderPassword=<your email password of 16digit which you get from Google settings>
fromEmail=<your email address>	
toEmail=<receiver email address , seperated>
emailSubject=<Subject of email based on project your are working on>
pdfReport=<Pdf report name>

Step2:
src>>test>>resources>>extent.properties

update below value:
extent.reporter.pdf.out=test-output/PdfReport/<Pdf report name>

PS: pdf report name should be same under emailConfig and extent.properties file
======================================================================================
