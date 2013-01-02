package com.kreig133.sms.grabber.model;

import com.google.gson.Gson;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author kreig133
 * @version 1.0
 */
public class TransactionJsonTest {

    @Test
    public void test() {
        Transaction transaction = new Transaction();
        transaction.id = 12345;
        Gson gson = new Gson();
        Assert.assertEquals( "{\"id\":12345}",  gson.toJson(transaction) );
    }
}
