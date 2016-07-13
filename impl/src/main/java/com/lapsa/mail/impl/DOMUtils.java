package com.lapsa.mailutil.impl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public final class DOMUtils {

    public static final String DOM_IMPLEMENTATION_VERSION = "XML 3.0";

    private static final DOMUtils INSTANCE = new DOMUtils();

    private final DOMImplementationRegistry domImplementationRegistry;
    private final DOMImplementation domImplementation;
    private final DOMImplementationLS domImplementationLS;

    private DOMUtils() {
	try {
	    domImplementationRegistry = DOMImplementationRegistry.newInstance();
	} catch (ClassNotFoundException e) {
	    throw new RuntimeException(e);
	} catch (InstantiationException e) {
	    throw new RuntimeException(e);
	} catch (IllegalAccessException e) {
	    throw new RuntimeException(e);
	} catch (ClassCastException e) {
	    throw new RuntimeException(e);
	}
	domImplementation = domImplementationRegistry.getDOMImplementation(DOM_IMPLEMENTATION_VERSION);
	domImplementationLS = (DOMImplementationLS) domImplementation;

    }

    public static DOMUtils getInstance() {
	return INSTANCE;
    }

    @Deprecated
    public static final Document createDocument(String namespaceURI, String qualifiedName) {
	return DOMUtils.getInstance().createDoc(namespaceURI, qualifiedName);
    }

    public final Document createDoc(String namespaceURI, String qualifiedName) {
	return domImplementation.createDocument(namespaceURI, qualifiedName, null);
    }

    @Deprecated
    public static final Document createDocument(String qualifiedName) {
	return DOMUtils.getInstance().createDoc(qualifiedName);
    }

    public final Document createDoc(String qualifiedName) {
	return domImplementation.createDocument(null, qualifiedName, null);
    }

    @Deprecated
    public static final String writeToString(Document doc, String xmlEncoding) throws UnsupportedEncodingException {
	return DOMUtils.getInstance().getAsString(doc, xmlEncoding);
    }

    public final String getAsString(Document doc, String xmlEncoding) throws UnsupportedEncodingException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	writeToOutputStream(doc, baos, xmlEncoding);
	return baos.toString(Charset.defaultCharset().name());
    }

    @Deprecated
    public static final boolean writeToByteStream(Document doc, OutputStream os, String xmlEncoding) {
	return DOMUtils.getInstance().writeToOutputStream(doc, os, xmlEncoding);
    }

    public final boolean writeToOutputStream(Document doc, OutputStream os, String xmlEncoding) {
	LSOutput lsOutput = domImplementationLS.createLSOutput();
	lsOutput.setByteStream(os);
	lsOutput.setEncoding(xmlEncoding);
	return _save(lsOutput, doc.getDocumentElement());
    }

    private boolean _save(LSOutput lsOutput, Node node) {
	LSSerializer lsSerializer = _getLSSerializer();
	return lsSerializer.write(node, lsOutput);
    }

    private LSSerializer _getLSSerializer() {
	return domImplementationLS.createLSSerializer();
    }

}
