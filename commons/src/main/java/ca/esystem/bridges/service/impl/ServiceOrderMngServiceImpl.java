package ca.esystem.bridges.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.esystem.framework.service.AbstractService;
import ca.esystem.bridges.dao.ServiceOrderDao;
import ca.esystem.bridges.domain.ServiceSchedule;
import ca.esystem.bridges.domain.Service_Order;
import ca.esystem.bridges.domain.Service_Order_Rate;
import ca.esystem.bridges.service.ServiceOrderMngService;

/**
 * Implementation for ServiceOrderMngService.
 * 
 * @author Lei Han
 *
 */
@Service("ServiceOrderMngService")
@Transactional
public class ServiceOrderMngServiceImpl extends AbstractService implements
		ServiceOrderMngService {

	@Resource
	private ServiceOrderDao dao;

	public List<?> queryList(Object condition) {
		return dao.queryListByCondition(condition);
	}

	public int queryCount(Object condition) {
		return dao.queryCountRowsByCondition(condition);
	}

	@SuppressWarnings("unchecked")
	public Object queryOne(Object condition) {
		Service_Order object = (Service_Order) condition;
		Service_Order objectToReturn = (Service_Order) dao
				.queryObjectByCondition(object);

		ServiceSchedule queryCondition = new ServiceSchedule();
		queryCondition.setOrder_id(object.getOrder_id());

		List<ServiceSchedule> listResult = (List<ServiceSchedule>) dao
				.queryServiceScheduleList(queryCondition);

		objectToReturn.setServiceScheduleList(listResult);
		// objectToReturn = convdertTaxRate(objectToReturn, false);
		return objectToReturn;
	}

	public Object add(Object obj) {
		Service_Order object = (Service_Order) obj;
		object = convdertTaxRate(object, true);
		
		List<ServiceSchedule> listResult = object.getServiceScheduleList();
		object.setServiceScheduleList(null);
		
		dao.insert(object);
		
		Integer order_id = object.getOrder_id();

		saveScheduleList(listResult, order_id);

		return dao.queryObjectByCondition(obj);
	}

	@SuppressWarnings("unchecked")
	public int update(Object obj) {
		Service_Order object = (Service_Order) obj;
		int order_id = object.getOrder_id();

		dao.update(object);

		List<ServiceSchedule> listResult = object.getServiceScheduleList();

		ServiceSchedule queryCondition = new ServiceSchedule();
		queryCondition.setOrder_id(order_id);

		List<ServiceSchedule> oldServiceScheduleList = (List<ServiceSchedule>) dao
				.queryServiceScheduleList(queryCondition);
		for (ServiceSchedule oldServiceSchedule : oldServiceScheduleList) {
			dao.deleteServiceSchedule(oldServiceSchedule);
		}

		saveScheduleList(listResult, order_id);

		return 1;
	}

	public int archive(Object obj) {
		return 0;
	}

	public int delete(Object obj) {
		Service_Order object = (Service_Order) obj;

		return dao.delete(object);
	}

	private Service_Order convdertTaxRate(Service_Order obj, boolean isToDB) {
		DecimalFormat fmt = new DecimalFormat("0.00");
		/*
		 * if (isToDB) { obj.setGst_rate(Float.parseFloat(obj.getGstRate()) /
		 * 100); obj.setPst_rate(Float.parseFloat(obj.getPstRate()) / 100); }
		 * else { obj.setGstRate(fmt.format(obj.getGst_rate() * 100));
		 * obj.setPstRate(fmt.format(obj.getPst_rate() * 100)); }
		 */
		return obj;
	}

	private void saveScheduleList(List<ServiceSchedule> listResult, int order_id) {
		if (listResult != null) {
			for (ServiceSchedule serviceSchedule : listResult) {
				serviceSchedule.setOrder_id(order_id);
				Date serviceTime = serviceSchedule
						.getServiceTimeFromDateTimePartial();
				if (serviceTime != null) {
					serviceSchedule.setServicetime(serviceTime);
					dao.insertServiceSchedule(serviceSchedule);
				}
			}
		}
	}

	public Service_Order_Rate getOrderRate(Service_Order_Rate orderRate) {
		return dao.getOrderRate(orderRate);
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List queryListForProfessing(Object condition) {
		return dao.queryListForProfessingByCondition(condition);
	}

	public int updateStatus(Object obj) {
		return dao.updateStatus(obj);
	}

	@Transactional(readOnly = true)
	public Service_Order queryOrderById(Integer orderId) {
		Service_Order condition = new Service_Order();
		condition.setOrder_id(orderId);
		Service_Order ret = (Service_Order) dao
				.queryObjectByCondition(condition);

		return ret;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ServiceSchedule> queryScheduleListByOrder(Integer orderId) {
		ServiceSchedule condition = new ServiceSchedule();
		condition.setOrder_id(orderId);

		return (List<ServiceSchedule>) dao.queryServiceScheduleList(condition);
	}

	public List<Service_Order> querySupplierOrderListByUserId(
			Service_Order order) {
		return dao.querySupplierOrderListByUserId(order);
	}

	public void updateOrderStatus(Service_Order order) {
		dao.updateOrderStatus(order);
	}

	public int insertOrderRate(Service_Order_Rate orderRate) {
		return dao.insertOrderRate(orderRate);
	}
}
