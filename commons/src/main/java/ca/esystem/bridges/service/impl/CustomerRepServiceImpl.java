package ca.esystem.bridges.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import ca.esystem.bridges.dao.CustomerRepDao;
import ca.esystem.bridges.domain.BloodType;
import ca.esystem.bridges.domain.GenderType;
import ca.esystem.bridges.domain.UserAccountStatusType;
import ca.esystem.bridges.service.CustomerRepService;
import ca.esystem.framework.service.AbstractService;

@Service("CustomerRepService")
public class CustomerRepServiceImpl extends AbstractService implements CustomerRepService {
    @Resource
    private CustomerRepDao customerRepDao;
    
    @ModelAttribute("accountStatusMap")
    public Map<Integer, String> accountStatusOptions() {

        Map<Integer, String> statusMap = new HashMap<Integer, String>();
        statusMap.put(UserAccountStatusType.active.getCode(), UserAccountStatusType.active.getName());
        statusMap.put(UserAccountStatusType.inactive.getCode(), UserAccountStatusType.inactive.getName());
        statusMap.put(UserAccountStatusType.locked.getCode(), UserAccountStatusType.locked.getName());

        return statusMap;
    }

    @ModelAttribute("genderMap")
    public Map<String, String> genderOptions() {

        Map<String, String> genderMap = new HashMap<String, String>();
        genderMap.put(GenderType.male.getCode(), GenderType.male.getName());
        genderMap.put(GenderType.female.getCode(), GenderType.female.getName());

        return genderMap;
    }

    @ModelAttribute("bloodTypeMap")
    public Map<String, String> bloodTypeOptions() {

        Map<String, String> bloodTypeMap = new HashMap<String, String>();
        bloodTypeMap.put(BloodType.A.getCode(), BloodType.A.getName());
        bloodTypeMap.put(BloodType.B.getCode(), BloodType.B.getName());
        bloodTypeMap.put(BloodType.AB.getCode(), BloodType.AB.getName());
        bloodTypeMap.put(BloodType.O.getCode(), BloodType.O.getName());

        return bloodTypeMap;
    }

    @ModelAttribute("yearMap")
    public SortedMap<Integer, String> yearOptions() {

        SortedMap<Integer, String> yearMap = new TreeMap<Integer, String>();
        int cy = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = cy - 100; year < cy; year++) {
            yearMap.put(year, String.format("%d年", year));
        }

        return yearMap;
    }

    @ModelAttribute("monthMap")
    public SortedMap<Integer, String> monthOptions() {

        SortedMap<Integer, String> monthMap = new TreeMap<Integer, String>();
        for (int month = 1; month <= 12; month++) {
            monthMap.put(month, String.format("%d月", month));
        }

        return monthMap;
    }

    @ModelAttribute("dayOfMonthMap")
    public SortedMap<Integer, String> dayOfMonthOptions() {

        SortedMap<Integer, String> dayOfMonthMap = new TreeMap<Integer, String>();
        for (int day = 1; day <= 31; day++) {
            dayOfMonthMap.put(day, String.format("%d日 ", day));
        }

        return dayOfMonthMap;
    }


    public List queryList(Object condition) {
        // TODO Auto-generated method stub
        return customerRepDao.queryListByCondition(condition);
    }

    public int queryCount(Object condition) {
        // TODO Auto-generated method stub
        return customerRepDao.queryCountRowsByCondition(condition);
    }

    public Object queryOne(Object condition) {
        // TODO Auto-generated method stub
        return customerRepDao.queryObjectByCondition(condition);
    }

    public Object add(Object obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public int update(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int archive(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int delete(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

}
