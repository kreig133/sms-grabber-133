package com.kreig133.sms.grabber.uralsib.grabber;

import com.kreig133.sms.grabber.model.Operation;
import com.kreig133.sms.grabber.model.RawParseInfo;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author kreig133
 * @version 1.0
 */
public class UralsibGrabberTest {

    @Test
    public void getInfoFromSmsTestCredit() {
        RawParseInfo infoFromSms = UralsibGrabber.getInfoFromSms(
                "BANK URALSIB.POSTUPLENIE SREDSTV NA SCHET: 22 012.00 RUR 2012-10-15 00:00:00;Ostatok 35 028.96 RUR" );
        Assert.assertEquals( infoFromSms.currency, "RUR" );
        Assert.assertNull( infoFromSms.payee );
        Assert.assertEquals( infoFromSms.value, "22 012.00" );
        Assert.assertNull( infoFromSms.cardNumber );
        Assert.assertEquals( infoFromSms.date, "2012-10-15" );
        Assert.assertEquals( infoFromSms.operation, Operation.CREDIT );
    }

    @Test
    public void getInfoFromSmsTestDebit() {
        RawParseInfo infoFromSms = UralsibGrabber.getInfoFromSms(
                "BANK URALSIB.SPISANIE SREDSTV SO SCHETA: 6 200.00 RUR 2012-12-29 13:43:06;Ostatok 16 733.50 RUR" );
        Assert.assertEquals( infoFromSms.currency, "RUR" );
        Assert.assertNull( infoFromSms.payee );
        Assert.assertEquals( infoFromSms.value, "6 200.00" );
        Assert.assertNull( infoFromSms.cardNumber );
        Assert.assertEquals( infoFromSms.date, "2012-12-29" );
        Assert.assertEquals( infoFromSms.operation, Operation.DEBIT );
    }

    @Test
    public void getInfoFromSmsTestReplenishment() {
        RawParseInfo infoFromSms = UralsibGrabber.getInfoFromSms(
                "BANK URALSIB.Popolnenie raskhodnogo limita card 4476*4665 20 000.00 RUROstatok 28 231.90 RUR" );
        Assert.assertEquals( infoFromSms.currency, "RUR" );
        Assert.assertNull( infoFromSms.payee );
        Assert.assertEquals( infoFromSms.value, "20 000.00" );
        Assert.assertEquals( infoFromSms.cardNumber, "4665" );
        Assert.assertNull( infoFromSms.date );
        Assert.assertEquals( infoFromSms.operation, Operation.REPLENISHMENT );
    }

    @Test
    public void getInfoFromSmsTestPayment() {
        RawParseInfo infoFromSms = UralsibGrabber.getInfoFromSms(
                "BANK URALSIB.4476***4665 2012-11-27 14:30:07 summa 225.80 RUR;APTEKA 145 in UFA,RUS;Ostatok 31 229.70 RUR" );
        Assert.assertEquals( infoFromSms.currency, "RUR" );
        Assert.assertEquals( infoFromSms.payee, "APTEKA 145" );
        Assert.assertEquals( infoFromSms.value, "225.80" );
        Assert.assertEquals( infoFromSms.cardNumber, "4665" );
        Assert.assertEquals( infoFromSms.date, "2012-11-27" );
        Assert.assertEquals( infoFromSms.operation, Operation.PAYMENT );
    }

    @Test
    public void getInfoFromSmsTestRedemptionOf() {
        RawParseInfo infoFromSms = UralsibGrabber.getInfoFromSms(
                "BANK URALSIB.4476***4665 2012-12-01 15:33:01 summa 300.00 RUR;ATM-01009412 URALSIB in UFA,RUS;Ostatok 28 435.81 RUR" );
        Assert.assertEquals( infoFromSms.cardNumber, "4665" );
        Assert.assertEquals( infoFromSms.date, "2012-12-01" );
        Assert.assertEquals( infoFromSms.value, "300.00" );
        Assert.assertEquals( infoFromSms.payee, "ATM-01009412 URALSIB" );
        Assert.assertEquals( infoFromSms.currency, "RUR" );
        Assert.assertEquals( infoFromSms.operation, Operation.REDEMPTION_OF );
    }

}
