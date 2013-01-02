package com.kreig133.sms.grabber.model;

import com.kreig133.sms.grabber.model.catalog.Currency;
import com.kreig133.sms.grabber.model.interpretator.ReplenishmentRawParseInfoInterpretator;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author kreig133
 * @version 1.0
 */
public class ReplenishmentRawParseInfoInterpretatorTest {
    @Test
    public void testInterpretate() throws Exception {
        RawParseInfo infoFromSms = new RawParseInfo();
        infoFromSms.currency = "RUR";
        infoFromSms.value = "20 000.00";
        infoFromSms.cardNumber =  "4665";
        infoFromSms.date  = "2012-01-10";
        infoFromSms.operation = Operation.REPLENISHMENT;

        ReplenishmentRawParseInfoInterpretator interpretator = new ReplenishmentRawParseInfoInterpretator();

        Transaction interpretate = (Transaction) interpretator.interpretate( infoFromSms );
        Assert.assertEquals( interpretate.income, 20000.0 );
        Assert.assertEquals( interpretate.outcome, 20000.0 );
        Assert.assertEquals( interpretate.instrument_outcome.intValue(), Currency.RUR.getId() );
        Assert.assertEquals( interpretate.instrument_income .intValue(), Currency.RUR.getId() );
    }
}
