package com.metrobank.mail.test;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class CheckMails {

    public static void check(String host, String storeType, String user, String password) {
	try {
	    Properties properties = new Properties();

	    properties.put("mail.store.protocol", "imaps");
	    properties.put("mail.imaps.host", host);
	    properties.put("mail.imaps.port", "993");
	    properties.put("mail.imaps.starttls.enable", "true");
	    Session emailSession = Session.getDefaultInstance(properties);

	    // create the POP3 store object and connect with the pop server
	    Store store = emailSession.getStore();

	    store.connect(host, user, password);

	    // create the folder object and open it
	    Folder emailFolder = store.getDefaultFolder();
	    emailFolder.open(Folder.READ_ONLY);

	    // retrieve the messages from the folder in an array and print it
	    Message[] messages = emailFolder.getMessages();
	    System.out.println("messages.length---" + messages.length);

	    for (int i = 0, n = messages.length; i < n; i++) {
		Message message = messages[i];
		System.out.println("---------------------------------");
		System.out.println("Email Number " + (i + 1));
		System.out.println("Subject: " + message.getSubject());
		System.out.println("From: " + message.getFrom()[0]);
		System.out.println("Text: " + message.getContent().toString());
	    }

	    // close the store and folder objects
	    emailFolder.close(false);
	    store.close();

	} catch (NoSuchProviderException e) {
	    e.printStackTrace();
	} catch (MessagingException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	String host = "imap.gmail.com";// change accordingly
	String mailStoreType = "imap";
	String username = "eurasiachecker@gmail.com";// change accordingly
	String password = "eurasiachecker2015";// change accordingly

	check(host, mailStoreType, username, password);

    }

}