package ca.esystem.bridges.integration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ca.esystem.bridges.http.SMSRequest;

public class SMSClient {
   public boolean sendSMS(String url, SMSRequest request) {
           RestTemplate template = new RestTemplate();
           
           ResponseEntity<Boolean> ret = template.postForEntity(url, request, Boolean.class);
           
           return ret.getBody();
   }

}
