package ca.esystem.bridges.service;

/**
 * This is composite of low level services, such as OrderService, and SMS client.
 *
 */
public interface OrderProcessingService {
    
    /**
     * Receive SMS from customer or provider about an order. Process order based on response type. 
     * Bid, unbid, complete or cancel an order.
     */
    public boolean receiveOrderSMS(String phoneNumber, String orderId, String message);
    /**
     * Send SMS to provider ask for bid on an order, message is composed based on status.
     * Change order status after sending SMS successfully.
     */
    public boolean sendAskForBidSMS(Integer operatorId, String memberId, Integer orderId);
    
    /**
     * Select 3 providers and send SMS to providers ask for bid on an order, message is composed based on status.
     * Change order status after sending SMS successfully.
     */
    public boolean sendAskForBidSMS(Integer operatorId, Integer orderId);
    
    /**
     * Send SMS to customer about status of an order, message is composed based on status.
     */
    public boolean sendOrderStatusSMS(String memberId, Integer orderId);
    
    /**
     * Process pending orders, re-send SMS and etc. This is for batch processing.
     */
    public boolean processPendingOrder();
    
    /**
     * Complete an order by customer service
     */
    public boolean updateOrderStatus(Integer operatorId, Integer orderId, String status);
}
