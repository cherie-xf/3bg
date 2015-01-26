package ca.esystem.bridges.sysio.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ca.esystem.bridges.http.SMSRequest;

public class SMSClient {
    private static final Logger logger = LoggerFactory.getLogger(SMSClient.class.getName());

    public static boolean sendSMS(String url, SMSRequest request) {
        RestTemplate template = new RestTemplate();

        try {

            ResponseEntity<Boolean> ret = template.postForEntity(url, request, Boolean.class);

            return ret.getBody();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }
    }

}
