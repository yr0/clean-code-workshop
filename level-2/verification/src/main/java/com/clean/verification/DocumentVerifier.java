package com.clean.verification;

import java.util.Properties;
import com.clean.verification.utils.EmailSender;
import com.clean.verification.utils.FraudClient;
import java.util.Map;

class DocumentVerifier {
  // Document to be verified
  Map<String, String> d;
  EmailSender email;

  DocumentVerifier(Map<String, String> d) {
    Properties properties = new Properties();
    this.d = d;
    // Provide properties for email
    properties.put("mail.smtp.host",       "smtp://booking.com");
    properties.put("mail.smtp.port",       "465");
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth",       "true");
    properties.put("mail.smtp.pass",       "abcde1234");
    this.email = new EmailSender(properties);
  }

  public String checkStatus() {
    if (incorrectTitle()) {
      // System.out.println(sendEmail("You have to provide title with your document"));
      sendEmail("You have to provide a descriptive title of your document");
      return "REJECTED_BAD_TITLE";
    }

    if (!correctUrl()) {
      sendEmail("Please provide a url where your document can be downloaded");
      return "REJECTED_BAD_URL";
    }

    if (!properPerson()) {
      sendEmail("Please provide the name of the person to whom the document belongs to");
      return "REJECTED_BAD_OWNER";
    }

    if (this.d.get("owner").equals("Willy Wonka") || this.d.get("owner").equals("Fraudulent Owner")) {
      sendEmail("Please hold");
      new FraudClient().register(this.d);
      return "REJECTED_FRAUD";
    }

    sendEmail("Your document has been verified");
    return "VERIFIED";

    // log the changes
    // if document is marked as fraudulent - send email to admin
  }

  public boolean correctUrl() {
    return this.d.get("url") != null && this.d.get("url") != "";
  }

  private boolean incorrectTitle() {
    return this.d.get("title") == "" || this.d.get("title") == null;
  }

  // I'm not sure why we have separate methods for these checks, but I decided to follow the tradition to avoid bugs
  private boolean properPerson() {
    return this.d.get("owner") != "" && this.d.get("owner") != null;
  }

  private String sendEmail(String message) {
    if (this.email.send(message, this.d.get("email"))) {
      return "Success";
    } else {
      return "Failed";
    }
  }
}
