package com.clean.verification.utils;

import java.util.Properties;

public class EmailSender {
  Properties p;

  public EmailSender(Properties p) {
    this.p = p;
  }

  public boolean send(String message, String email) {
    // TODO: Send the actual email
    return true;
  }
}
