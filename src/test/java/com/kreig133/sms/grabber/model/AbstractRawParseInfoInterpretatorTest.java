package com.kreig133.sms.grabber.model;

import com.kreig133.sms.grabber.model.interpretator.AbstractRawParseInfoInterpretator;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author kreig133
 * @version 1.0
 */
public class AbstractRawParseInfoInterpretatorTest {

    @Test
    public void testParseDate() throws Exception {
        Assert.assertNotNull( AbstractRawParseInfoInterpretator.parseDate( "2012-10-15" ) );
    }

    @Test
    public void testParseValue() throws Exception {
        Assert.assertEquals( AbstractRawParseInfoInterpretator.parseValue( "22 012.00" ), 22012.0 );
    }
}
