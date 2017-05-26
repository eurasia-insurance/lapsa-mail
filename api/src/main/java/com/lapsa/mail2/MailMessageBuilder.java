package com.lapsa.mail2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.w3c.dom.Document;

public interface MailMessageBuilder {

    MailMessageBuilder withBytesAttached(byte[] binaryData, String mimeType, String fileName)
	    throws MailBuilderException;

    MailMessageBuilder withBytesAttached(byte[] binaryData, String mimeType, String fileName, String contentId)
	    throws MailBuilderException;

    MailMessageBuilder withDocumentAttached(Document doc, String fileName) throws MailBuilderException;

    MailMessageBuilder withDocumentAttached(Document doc, String fileName, Charset charset)
	    throws MailBuilderException;

    MailMessageBuilder withDocumentAttached(Document doc, String fileName, Charset charset, String contentId)
	    throws MailBuilderException;

    MailMessageBuilder withDocumentAttached(Document doc, String fileName, String contentId)
	    throws MailBuilderException;

    MailMessageBuilder withExceptionAttached(Exception e, String fileName) throws MailBuilderException;

    MailMessageBuilder withAttachement(Exception e, String fileName, String contentId)
	    throws MailBuilderException;

    MailMessageBuilder withFileAttached(File file) throws MailBuilderException, IOException;

    MailMessageBuilder withFileAttached(File file, String contentId) throws MailBuilderException, IOException;

    MailMessageBuilder withStreamAttached(InputStream inputStream, String mimeType, String fileName)
	    throws MailBuilderException, IOException;

    MailMessageBuilder withStreamAttached(InputStream inputStream, String mimeType, String fileName, String contentId)
	    throws MailBuilderException, IOException;

    MailMessageBuilder withTextAttached(String textContent, String fileName)
	    throws MailBuilderException;

    MailMessageBuilder withTextAttached(String textContent, String fileName, Charset charset)
	    throws MailBuilderException;

    MailMessageBuilder withTextAttached(String textContent, String fileName, Charset charset, String contentId)
	    throws MailBuilderException;

    MailMessageBuilder withAnotherBCCRecipient(String address) throws MailBuilderException;

    MailMessageBuilder withAnotherBCCRecipient(String address, String friendlyName) throws MailBuilderException;

    MailMessageBuilder withAnotherCCRecipient(String address) throws MailBuilderException;

    MailMessageBuilder withAnotherCCRecipient(String address, String friendlyName) throws MailBuilderException;

    MailMessageBuilder withDocumentPart(Document doc) throws MailBuilderException;

    MailMessageBuilder withDocumentPart(Document doc, Charset charset) throws MailBuilderException;

    MailMessageBuilder withDocumentPart(Document doc, Charset charset, String contentId)
	    throws MailBuilderException;

    MailMessageBuilder withDocumentPart(Document doc, String contentId) throws MailBuilderException;

    MailMessageBuilder withExceptionPart(Exception e) throws MailBuilderException;

    MailMessageBuilder withExceptionPart(Exception e, String contentId) throws MailBuilderException;

    MailMessageBuilder withHtmlPart(String html) throws MailBuilderException;

    MailMessageBuilder withHtmlPart(String html, Charset charset) throws MailBuilderException;

    MailMessageBuilder withHtmlPart(String html, Charset charset, String contentId) throws MailBuilderException;

    MailMessageBuilder withHtmlPart(String html, String contentId) throws MailBuilderException;

    MailMessageBuilder withBinaryPart(byte[] binaryData, String mimeType)
	    throws MailBuilderException;

    MailMessageBuilder withBinaryPart(byte[] binaryData, String mimeType, String contentId)
	    throws MailBuilderException;

    MailMessageBuilder withFilePart(File file) throws MailBuilderException, IOException;

    MailMessageBuilder withFilePart(File file, String contentId) throws MailBuilderException, IOException;

    MailMessageBuilder withStreamPart(InputStream inputStream, String mimeType)
	    throws MailBuilderException, IOException;

    MailMessageBuilder withStreamPart(InputStream inputStream, String mimeType, String contentId)
	    throws MailBuilderException, IOException;

    MailMessageBuilder withTextPart(String text) throws MailBuilderException;

    MailMessageBuilder withTextPart(String text, Charset charset) throws MailBuilderException;

    MailMessageBuilder withTextPart(String text, Charset charset, String contentId) throws MailBuilderException;

    MailMessageBuilder withTextPart(String text, String contentId) throws MailBuilderException;

    MailMessageBuilder withAnotherTORecipient(String address) throws MailBuilderException;

    MailMessageBuilder withAnotherTORecipient(String address, String friendlyName) throws MailBuilderException;

    MailMessage build() throws MailBuilderException;

    MailMessageBuilder withBCCRecipient(String address) throws MailBuilderException;

    MailMessageBuilder withBCCRecipient(String address, String friendlyName) throws MailBuilderException;

    MailMessageBuilder withCCRecipient(String address) throws MailBuilderException;

    MailMessageBuilder withCCRecipient(String address, String friendlyName) throws MailBuilderException;

    MailMessageBuilder withCharset(Charset defaultCharset) throws MailBuilderException;

    MailMessageBuilder withSender(String address) throws MailBuilderException;

    MailMessageBuilder withSender(String address, String friendlyName) throws MailBuilderException;

    MailMessageBuilder withSubject(String subject) throws MailBuilderException;

    MailMessageBuilder withTORecipient(String address) throws MailBuilderException;

    MailMessageBuilder withTORecipient(String address, String friendlyName) throws MailBuilderException;

    MailMessageBuilder withDefaultRecipient() throws MailBuilderException;

    MailMessageBuilder withDefaultSender() throws MailBuilderException;

    MailMessageBuilder withDefaultCharset() throws MailBuilderException;

}
