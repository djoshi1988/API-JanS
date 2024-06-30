package com.bank.cedrus.service.impl;

import java.io.IOException;
import java.util.stream.IntStream;

import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.cedrus.common.ISOServiceConfiguration;
import com.bank.cedrus.iso.ISOMsgMapper;
import com.bank.cedrus.iso.ISORequest;
import com.bank.cedrus.iso.ISOResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ISOService {

    @Autowired
    private ISOServiceConfiguration isoServiceConfiguration;

    public ISOResponse executeISOService(ISORequest isoRequest) {
        try {
            ISOMsg isoMsg = createISOMessage(isoRequest);
            String isoResponse = sendMessageAndReceiveResponse(isoServiceConfiguration,isoMsg);
            return parseISOResponse(isoResponse);
        } catch (Exception e) {
            log.error("Error executing ISO service: {}", e.getMessage());
            return null;
        }
    }

    public ISOMsg createISOMessage(ISORequest isoRequest) throws Exception {
       
    	ISOMsgMapper mapper=new ISOMsgMapper();
    	ISOMsg isoMsg = mapper.mapISORequestToISOMsg(isoRequest);
        printISOMessage(isoMsg);
        return isoMsg;
    }

    public String sendMessageAndReceiveResponse(ISOServiceConfiguration isoServiceConfiguration, ISOMsg isoMsg) throws ISOException {
    	String isoResponse ="";
    	
    	try {
        	ASCIIChannel asciiChannel = createAndConnectChannel(isoServiceConfiguration, isoMsg) ;
            log.info("Connected");
            
            asciiChannel.send(isoMsg.pack());
            log.info("ISO Message Sent");
            
            isoResponse = receiveResponse(asciiChannel);
            
            asciiChannel.disconnect();
            log.info("ISO Response: {}", isoResponse);
        } catch (IOException e) {
            log.error("Error occurred during communication: {}", e.getMessage());
        }
    	return isoResponse;
    }

    private ASCIIChannel createAndConnectChannel(ISOServiceConfiguration isoServiceConfiguration, ISOMsg isoMsg) throws IOException {
        ISOChannel asciiChannel = new ASCIIChannel(isoServiceConfiguration.getAddress(), isoServiceConfiguration.getPort(), isoMsg.getPackager());
        ((ASCIIChannel) asciiChannel).setTimeout(isoServiceConfiguration.getConnectionTimeout());
        ((ASCIIChannel) asciiChannel).connect();
        return (ASCIIChannel) asciiChannel;
    }

    private String receiveResponse(ASCIIChannel asciiChannel) throws IOException {
        byte[] responseBytes = new byte[10000];
        int bytesRead = asciiChannel.getBytes(responseBytes);
        return new String(responseBytes, 0, bytesRead);
    }


    public ISOResponse parseISOResponse(String isoResponse) throws Exception {
    	ISOMsgMapper mapper=new ISOMsgMapper();
    	return mapper.mapResponseToISOResponse(isoResponse);
    }
    
    private void printISOMessage(ISOMsg isoMsg) {
        try {
            log.info("MTI = {}", isoMsg.getMTI());
            IntStream.rangeClosed(1, isoMsg.getMaxField())
                     .filter(isoMsg::hasField)
                     .forEach(i -> log.info("Field ({}) = {}", i, getFieldAsString(isoMsg, i)));
        } catch (Exception e) {
            log.error("Error printing ISO message: {}", e.getMessage());
        }
    }

    private String getFieldAsString(ISOMsg isoMsg, int fieldNumber) {        
            return isoMsg.getString(fieldNumber);        
    }
}
