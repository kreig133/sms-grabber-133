package com.kreig133.sms.grabber;

import com.kreig133.sms.grabber.model.RawParseInfo;
import com.kreig133.sms.grabber.model.Request;

/**
 * @author kreig133
 * @version 1.0
 */
public interface Grabber {
    public Request parseSms(String smsText);
}
