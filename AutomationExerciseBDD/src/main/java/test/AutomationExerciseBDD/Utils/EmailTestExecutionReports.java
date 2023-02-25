package test.AutomationExerciseBDD.Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class EmailTestExecutionReports {

	public Properties props;
	public static Properties emailProperty;
	public Message msgContent;
	private MimeBodyPart messageBodyPart1;
	private MimeBodyPart messageBodyPart2;

	public EmailTestExecutionReports(Properties props){
		this.props = props;
	}
	
	public static void main(String[] args) throws Exception {
		emailProperty = new Efficacies().loadPropertyFile("emailConfig.properties");
		EmailTestExecutionReports email = new EmailTestExecutionReports(emailProperty);
		Session session = email.setBasicEmailConfiguration()
							   .createNewEmailSession();
		Message msg = email.setEmailMsgContent(session);
		email.sendEmail(msg, emailProperty.getProperty("pdfReport"));
	}

	public EmailTestExecutionReports setBasicEmailConfiguration() {

		switch (props.getProperty("smtpServer")) {
		case "Gmail":
			props.put("mail.smtp.host", "smtp.gmail.com");
			break;
		default:
			props.put("mail.smtp.host", "smtp.gmail.com");
			break;
		}

		// set the port of socket factory
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");
		return new EmailTestExecutionReports(props);
	}

	public Session createNewEmailSession() {
		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(props.getProperty("senderEmail"), props.getProperty("senderPassword"));
					}
				});
		return session;
	}

	public Message setEmailMsgContent(Session session) throws Exception {
		try {
			msgContent = new MimeMessage(session);
			msgContent.setFrom(new InternetAddress(props.getProperty("fromEmail")));
			msgContent.setRecipients(Message.RecipientType.TO, InternetAddress.parse(props.getProperty("toEmail")));
			msgContent.setSubject(props.getProperty("emailSubject"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return msgContent;
	}

	private MimeBodyPart emailBodyContent() throws Exception {
		try {
			messageBodyPart1 = new MimeBodyPart();
			pdf2Image(emailProperty.getProperty("pdfReport"));
			String msgBodyContent = "<html>\n"
					+ "<body>\n"
					+ "\n"
					+ "<h4>Hello Team</h4>\n"
					+ "\n"
					+ "<p>Automation Test Execution is completed successfully. Please find below summary report of Automation Test Execution:</p>\n"
					+ "\n"
					+ "<img height=\"400\" width=\"700\" src=\"cid:image\">"
					+ "\n"
					+ "<p> <b> PS: </b> For Detailed execution logs refer attached pdf execution report.</p>\n"
					+ "\n"
					+ "</body>\n"
					+ "</html>\n"
					+ "";
			messageBodyPart1.setContent(msgBodyContent,  "text/html");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return messageBodyPart1;
	}
	
	private MimeBodyPart addImgToBodyContent() throws MessagingException {
		messageBodyPart1 = new MimeBodyPart();

		String imageSource = System.getProperty("user.dir") + File.separator + "test-output" + File.separator
				+ "PdfReport" + File.separator + emailProperty.getProperty("pdfReport") + ".jpg";
		
		DataSource fds = new FileDataSource(imageSource);
		messageBodyPart1.setDataHandler(new DataHandler(fds));
		messageBodyPart1.addHeader("Content-ID", "<image>");
		return messageBodyPart1;
	}
	
	private MimeBodyPart emailAttachement(String pdfReportName) throws Exception{
		try {
			messageBodyPart2 = new MimeBodyPart();
			String fileName = System.getProperty("user.dir") + File.separator + "test-output" + File.separator
					+ "PdfReport" + File.separator + pdfReportName + ".pdf";
	
			DataSource source = new FileDataSource(fileName);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(fileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return messageBodyPart2;
	}
	
	public void sendEmail(Message msg, String pdfReport) throws Exception {
		try {
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(emailBodyContent());
			multipart.addBodyPart(addImgToBodyContent());
			multipart.addBodyPart(emailAttachement(pdfReport));

			// set the content and send email
			msg.setContent(multipart);
			Transport.send(msg);
			
			System.out.println("============ Email Sent Successfully =========");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	public void pdf2Image(String pdfReportName) throws IOException, InterruptedException {

		String fileName = System.getProperty("user.dir") + File.separator + "test-output" + File.separator
				+ "PdfReport" + File.separator + pdfReportName + ".pdf";
		File file = new File(fileName);
		
		PDDocument document = PDDocument.load(file);
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
		File outputFile = new File(System.getProperty("user.dir") + File.separator + "test-output" + File.separator
				+ "PdfReport" + File.separator + pdfReportName + ".jpg");
		ImageIO.write(bim, "jpg", outputFile);
		document.close();
	}

}
