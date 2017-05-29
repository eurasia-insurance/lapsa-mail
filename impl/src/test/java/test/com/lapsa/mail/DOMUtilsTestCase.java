package test.com.lapsa.mail;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lapsa.utils.DocumentUtils;

public class DOMUtilsTestCase {

    @Test
    public void testGetInstance() {
	DocumentUtils utils = DocumentUtils.getInstance();
	assertNotNull(utils);
    }

    @Test
    public void testTheSame() {
	DocumentUtils utils1 = DocumentUtils.getInstance();
	DocumentUtils utils2 = DocumentUtils.getInstance();

	assertSame(utils1, utils2);
    }
}
