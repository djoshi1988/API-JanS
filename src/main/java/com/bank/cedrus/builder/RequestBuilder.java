package com.bank.cedrus.builder;

import java.util.List;

import com.bank.cedrus.iso.ISORequest;
import com.bank.cedrus.model.request.Request;

public class RequestBuilder {
    public static <T> ISORequest buildISORequest(T form, String functionality,List<String> excludedFields) {
        ISORequest isoReq = new ISORequest();
        isoReq.setField125(Request.toFormString(form, functionality, excludedFields));
        return isoReq;
    }
}
