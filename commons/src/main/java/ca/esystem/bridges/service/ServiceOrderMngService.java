package ca.esystem.bridges.service;

import java.util.List;

import ca.esystem.bridges.domain.ServiceSchedule;
import ca.esystem.bridges.domain.Service_Order;
import ca.esystem.bridges.domain.Service_Order_Rate;
import ca.esystem.framework.service.BasicService;

/**
 * ServiceOrder Interface of ServiceOrder Management.
 * 
 * @author Lei Han
 *
 */
public interface ServiceOrderMngService extends BasicService {
	Service_Order_Rate getOrderRate(Service_Order_Rate orderRate);

	@SuppressWarnings("rawtypes")
	public List queryListForProfessing(Object condition);

	public int updateStatus(Object obj);

	public Service_Order queryOrderById(Integer orderId);

	public List<ServiceSchedule> queryScheduleListByOrder(Integer orderId);

	public List<Service_Order> querySupplierOrderListByUserId(
			Service_Order order);

	public void updateOrderStatus(Service_Order order);

	public int insertOrderRate(Service_Order_Rate orderRate);
}
