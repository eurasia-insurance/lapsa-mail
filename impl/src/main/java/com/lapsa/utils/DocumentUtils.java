package com.lapsa.utils;

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

public final class DocumentUtils {

    public static final String DOM_IMPLEMENTATION_VERSION = "XML 3.0";

    private static final DocumentUtils INSTANCE = new DocumentUtils();

    private final DOMImplementationRegistry domImplementationRegistry;
    private final DOMImplementation domImplementation;
    private final DOMImplementationLS domImplementationLS;

    private DocumentUtils() {
	try {
	    domImplementationRegistry = DOMImplementationRegistry.newInstance();
	} catch (final ClassNotFoundException e) {
	    throw new RuntimeException(e);
	} catch (final InstantiationException e) {
	    throw new RuntimeException(e);
	} catch (final IllegalAccessException e) {
	    throw new RuntimeException(e);
	} catch (final ClassCastException e) {
	    throw new RuntimeException(e);
	}
	domImplementation = domImplementationRegistry.getDOMImplementation(DOM_IMPLEMENTATION_VERSION);
	domImplementationLS = (DOMImplementationLS) domImplementation;

    }

    public static DocumentUtils getInstance() {
	return INSTANCE;
    }

    @Deprecated
    public static final Document createDocument(final String namespaceURI, final String qualifiedName) {
	return DocumentUtils.getInstance().createDoc(namespaceURI, qualifiedName);
    }

    public final Document createDoc(final String namespaceURI, final String qualifiedName) {
	return domImplementation.createDocument(namespaceURI, qualifiedName, null);
    }

    @Deprecated
    public static final Document createDocument(final String qualifiedName) {
	return DocumentUtils.getInstance().createDoc(qualifiedName);
    }

    public final Document createDoc(final String qualifiedName) {
	return domImplementation.createDocument(null, qualifiedName, null);
    }

    @Deprecated
    public static final String writeToString(final Document doc, final String xmlEncoding)
	    throws UnsupportedEncodingException {
	return DocumentUtils.getInstance().getAsString(doc, xmlEncoding);
    }

    public final String getAsString(final Document doc, final String xmlEncoding) throws UnsupportedEncodingException {
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	writeToOutputStream(doc, baos, xmlEncoding);
	return baos.toString(Charset.defaultCharset().name());
    }

    @Deprecated
    public static final boolean writeToByteStream(final Document doc, final OutputStream os, final String xmlEncoding) {
	return DocumentUtils.getInstance().writeToOutputStream(doc, os, xmlEncoding);
    }

    public final boolean writeToOutputStream(final Document doc, final OutputStream os, final String xmlEncoding) {
	final LSOutput lsOutput = domImplementationLS.createLSOutput();
	lsOutput.setByteStream(os);
	lsOutput.setEncoding(xmlEncoding);
	return _save(lsOutput, doc.getDocumentElement());
    }

    private boolean _save(final LSOutput lsOutput, final Node node) {
	final LSSerializer lsSerializer = _getLSSerializer();
	return lsSerializer.write(node, lsOutput);
    }

    private LSSerializer _getLSSerializer() {
	return domImplementationLS.createLSSerializer();
    }

}
