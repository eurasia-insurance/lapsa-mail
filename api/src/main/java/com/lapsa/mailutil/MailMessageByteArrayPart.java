package com.lapsa.mailutil;

public interface MailMessageByteArrayPart extends MailMessagePart {
	byte[] getBytes();

	String getContentType();

	String getName();

}
