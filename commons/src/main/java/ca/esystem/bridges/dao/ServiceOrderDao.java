package ca.esystem.bridges.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ca.esystem.bridges.domain.Service_Order;
import ca.esystem.bridges.domain.Service_Order_Rate;
import ca.esystem.framework.dao.BasicAccessDao;

/**
 * DAO for the table of service_order and related tables.
 * 
 * @author Lei Han
 *
 */

@Repository
public interface ServiceOrderDao extends BasicAccessDao {
	public int insertServiceSchedule(Object obj);

	public int deleteServiceSchedule(Object obj);

	public List<?> queryServiceScheduleList(Object obj);

	public Service_Order_Rate getOrderRate(Service_Order_Rate orderRate);

	@SuppressWarnings("rawtypes")
	public List queryListForProfessingByCondition(Object obj);

	public int updateStatus(Object obj);

	public List<Service_Order> querySupplierOrderListByUserId(
			Service_Order order);

	public void updateOrderStatus(Service_Order order);

	public int insertOrderRate(Service_Order_Rate orderRate);
}