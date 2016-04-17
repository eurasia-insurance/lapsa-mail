package com.lapsa.mailutil.xml.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lapsa.mailutil.impl.DOMUtils;

public class DOMUtilsTestCase {

    @Test
    public void testGetInstance() {
	DOMUtils utils = DOMUtils.getInstance();
	assertNotNull(utils);
    }

    @Test
    public void testTheSame() {
	DOMUtils utils1 = DOMUtils.getInstance();
	DOMUtils utils2 = DOMUtils.getInstance();

	assertSame(utils1, utils2);
    }
}