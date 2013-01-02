package com.kreig133.sms.grabber.model.interpretator;

import com.kreig133.sms.grabber.model.RawParseInfo;
import com.kreig133.sms.grabber.model.Request;
import com.kreig133.sms.grabber.model.Transaction;
import com.kreig133.sms.grabber.model.catalog.AccountType;
import com.kreig133.sms.grabber.model.catalog.Accounts;
import com.kreig133.sms.grabber.model.catalog.Currency;
import com.kreig133.sms.grabber.model.catalog.ReminderCategory;

/**
 * @author kreig133
 * @version 1.0
 */
public class CreditRawParseInfoInterpretator extends AbstractRawParseInfoInterpretator {
    @Override
    public Request interpretate( RawParseInfo parseInfo ) {
        Transaction transaction = new Transaction();

        transaction.account_income  = Accounts.getAccount( Accounts.DEFAULT );
        transaction.account_outcome = Accounts.getAccount( Accounts.DEFAULT );
        transaction.date = parseDate( parseInfo.date );
        transaction.income  = parseValue( parseInfo.value );
        transaction.outcome = 0.0;
        transaction.category = ReminderCategory.ZERO.getId();

        Currency currency = Currency.valueOf( parseInfo.currency );
        transaction.instrument_income  = currency.getId();
        transaction.instrument_outcome = currency.getId();

        transaction.type_outcome = AccountType.CCARD.getName();
        transaction.type_income  = AccountType.CCARD.getName();

        transaction.direction    = 1; //TODO WTF?!
        transaction.inbalance_outcome = true;
        transaction.inbalance_income  = true;

        transaction.tag_group = 232495; //TODO


        return transaction;
    }
}
