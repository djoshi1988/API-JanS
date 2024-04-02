package com.bank.cedrus.iso;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

public class ISOMsgMapper {
	
    private static final String FIELDS_XML_PATH = "/fields.xml";

    public ISOMsg mapISORequestToISOMsg(ISORequest isoRequest) throws ISOException {
    	ISOMsg isoMsg = new ISOMsg();
        try {
        	InputStream is = getClass().getResourceAsStream(FIELDS_XML_PATH);
            GenericPackager packager = new GenericPackager(is);
            
            isoMsg.setPackager(packager);
            isoMsg.setMTI(isoRequest.getMTI());
            
            Field[] fields = ISORequest.class.getDeclaredFields();
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    Object value = field.get(isoRequest);
                    int fieldNumber = extractFieldNumber(field.getName());
                    if (fieldNumber != -1) {
                        isoMsg.set(fieldNumber, value.toString());
                    }
                }
            }
        } catch (IllegalAccessException | ISOException e) {
            throw new ISOException("Error mapping ISORequest to ISOMsg: " + e.getMessage(), e);
        }
        return isoMsg;
    }
    
    
    public ISOResponse mapResponseToISOResponse(String response) throws ISOException {
    	ISOMsg isoMsg = new ISOMsg();
    	ISOResponse resp = new ISOResponse();
        try {
        	InputStream is = getClass().getResourceAsStream(FIELDS_XML_PATH);
            GenericPackager packager = new GenericPackager(is);
            
            isoMsg.setPackager(packager);
            isoMsg.unpack(response.getBytes());           
            resp.setResponseCode(isoMsg.getString(39));
            resp.setData(isoMsg.getString(127) != null ? Arrays.asList(isoMsg.getString(127).split("\\|")) : null);
            
        } catch (ISOException e) {
            throw new ISOException("Error mapping ISORequest to ISOMsg: " + e.getMessage(), e);
        }
        return resp;
    }
    
    private static int extractFieldNumber(String fieldName) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(fieldName);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
         return -1;
    }
}

