package ca.esystem.bridges.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.esystem.bridges.http.SMSRequest;
import ca.esystem.bridges.service.ContactService;
import ca.esystem.bridges.service.OrderProcessingService;
import ca.esystem.framework.web.controller.AbstractController;

@Controller
public class SMSController extends AbstractController {
    private final Logger           logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "OrderProcessingService")
    private OrderProcessingService orderProcessingService;

    @RequestMapping(value = "/system/sms", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody ResponseEntity<Boolean> recieve(@RequestBody SMSRequest request) {
        
        logger.debug("Received sms callback - phone number = {}, app id = {}, message body = {}.", request.getPhoneNumber(), request.getAppId(), request.getBody());
        try {
            orderProcessingService.receiveOrderSMS(request.getPhoneNumber(), request.getAppId(), request.getBody());
        }
        catch(Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);            
        }
        
        //
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

}
