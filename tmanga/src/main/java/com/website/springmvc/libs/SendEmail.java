package com.website.springmvc.libs;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.website.springmvc.config.MyConstants;

import java.io.IOException;

public class SendEmail {
	public static void main(String[] args) throws IOException {
		Email from = new Email("test@example.com");
		String subject = "Hello World from the SendGrid Java Library!";

		Email to = new Email("shareef@gmail.com");
		Content content = new Content("text/plain", "Hello, Email!");
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(MyConstants.SENDGRID_API_KEY);
		Request request = new Request();
		try {

			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());

		} catch (IOException ex) {
			throw ex;
		}
	}

	public static boolean sendGrid(String fromEmail, String toEmail, String subject, String contentString, boolean isHtml) {
		try {
			Email from = new Email(fromEmail);
			Email to = new Email(toEmail);
			Content content = null;
			if (isHtml == true) {
				content = new Content("text/html", contentString);
			} else {
				content = new Content("text/plain", contentString);
			}
			Mail mail = new Mail(from, subject, to, content);
			// SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
			SendGrid sg = new SendGrid(MyConstants.SENDGRID_API_KEY);	
			Request request = new Request();
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println("sendGrid to email = " + toEmail);
			System.out.println("sendGrid status code = " + response.getStatusCode());
			System.out.println("sendGrid body = " + response.getBody());
			System.out.println("sendGrid header = " + response.getHeaders());
		} catch (Exception ex) {
			System.out.println("Expcetion in sendEmail: " + ex.getMessage());
			return false;
		}
		return true;
	}

}