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

    private DocumentUtils() {
	throw new AssertionError("You can not instantiate this");
    }

    private static final String DOM_IMPLEMENTATION_VERSION = "XML 3.0";
    private static final DOMImplementationRegistry DOM_IMPLEMENTATION_REGISTRY;
    private static final DOMImplementation DOM_IMPLEMENTATION;
    private static final DOMImplementationLS DOM_IMPLEMENTATION_LS;
    private static final LSSerializer DOM_LS_SERIALIZER;

    static {
	try {
	    DOM_IMPLEMENTATION_REGISTRY = DOMImplementationRegistry.newInstance();
	} catch (final ClassNotFoundException | InstantiationException | IllegalAccessException
		| ClassCastException e) {
	    throw new RuntimeException(e);
	}
	DOM_IMPLEMENTATION = DOM_IMPLEMENTATION_REGISTRY.getDOMImplementation(DOM_IMPLEMENTATION_VERSION);
	DOM_IMPLEMENTATION_LS = (DOMImplementationLS) DOM_IMPLEMENTATION;
	DOM_LS_SERIALIZER = DOM_IMPLEMENTATION_LS.createLSSerializer();
    }

    public static Document createDocument(final String qualifiedName) {
	return DOM_IMPLEMENTATION.createDocument(null, qualifiedName, null);
    }

    public static Document createDocument(final String namespaceURI, final String qualifiedName) {
	return DOM_IMPLEMENTATION.createDocument(namespaceURI, qualifiedName, null);
    }

    public static String getAsString(final Document doc, final String xmlEncoding) throws UnsupportedEncodingException {
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	writeToOutputStream(doc, baos, xmlEncoding);
	return baos.toString(Charset.defaultCharset().name());
    }

    public static boolean writeToOutputStream(final Document doc, final OutputStream os, final String xmlEncoding) {
	final LSOutput lsOutput = DOM_IMPLEMENTATION_LS.createLSOutput();
	lsOutput.setByteStream(os);
	lsOutput.setEncoding(xmlEncoding);
	return _save(lsOutput, doc.getDocumentElement());
    }

    // PRIVATE

    private static boolean _save(final LSOutput lsOutput, final Node node) {
	return DOM_LS_SERIALIZER.write(node, lsOutput);
    }

}
