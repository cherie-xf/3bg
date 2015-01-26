package ca.esystem.bridges.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.esystem.bridges.service.OrderProcessingService;

@Service
public class OrderProcessingJob {
    private final Logger       logger = LoggerFactory.getLogger(this.getClass().getName());
    
    private OrderProcessingService        orderProcessingService;
    
    public void setOrderProcessingService(OrderProcessingService orderProcessingService) {
        this.orderProcessingService = orderProcessingService;
    }
    
    public boolean processOrder() {
        
        try {
            orderProcessingService.processPendingOrder();
        }
        catch(Exception ex) {
            logger.error(ex.getMessage());
        }
        
        //
        return true;
    }
}
