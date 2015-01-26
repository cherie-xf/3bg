package ca.esystem.bridges.web.controller;

import java.util.List;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.esystem.bridges.domain.ContactClass;
import ca.esystem.bridges.domain.Dictionary;
import ca.esystem.bridges.dao.DictionaryDao;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * 
 * @author Larry, Lei Han ,Cherie
 *
 */

@Controller
public class DictionaryController extends AbstractController {
    private final Logger        logger       = LoggerFactory.getLogger(this.getClass().getName());

    private final static String COUNTRY_CODE = "CAN";

    @Resource
    private DictionaryDao       dictionaryDao;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/system/refreshDictionary", method = RequestMethod.GET)
    public @ResponseBody String refreshDictionary(Model model, HttpServletRequest request) throws Exception {
        boolean reloadflag = false;
        String reload = request.getParameter("reload");
        if (reload != null && reload.equals("true")) {
            reloadflag = true;
        }
        logger.debug("Received refreshDictionary request");
        logger.debug("reloadflag=" + reloadflag);
        if (getAppScopeObj(request, "ticketStatusMap") == null || reloadflag) {
            List ticketStatusList = dictionaryDao.queryTicketStatusList();
            TreeMap ticketStatusMap = convertListtoMap(ticketStatusList);
            setAppScopeObj(request, "ticketStatusMap", ticketStatusMap);
        }

        if (getAppScopeObj(request, "sourceTypeMap") == null || reloadflag) {
            List sourceTypeList = dictionaryDao.queryTicketSourceType();
            TreeMap sourceTypeMap = convertListtoMap(sourceTypeList);
            setAppScopeObj(request, "sourceTypeMap", sourceTypeMap);
        }

        if (getAppScopeObj(request, "memberTypeMap") == null || reloadflag) {
            List memberTypeList = dictionaryDao.queryMemberTypeList();
            TreeMap memberTypeMap = convertListtoMap(memberTypeList);
            setAppScopeObj(request, "memberTypeMap", memberTypeMap);
        }

        if (getAppScopeObj(request, "customerMemberTypeMap") == null || reloadflag) {
            List memberTypeList = dictionaryDao.querySubMemberTypeList("C");
            TreeMap memberTypeMap = convertListtoMap(memberTypeList);
            setAppScopeObj(request, "customerMemberTypeMap", memberTypeMap);
        }

        if (getAppScopeObj(request, "businessMemberTypeMap") == null || reloadflag) {
            List memberTypeList = dictionaryDao.querySubMemberTypeList("B");
            TreeMap memberTypeMap = convertListtoMap(memberTypeList);
            setAppScopeObj(request, "businessMemberTypeMap", memberTypeMap);
        }
        
        if (getAppScopeObj(request, "CandBMemberTypeMap") == null || reloadflag) {
            List memberTypeList1 = dictionaryDao.querySubMemberTypeList("B");
            List memberTypeList2 = dictionaryDao.querySubMemberTypeList("C");
            memberTypeList1.addAll(memberTypeList2);
            TreeMap memberTypeMap = convertListtoMap(memberTypeList1);
            setAppScopeObj(request, "CandBMemberTypeMap", memberTypeMap);
        }

        if (getAppScopeObj(request, "innerMemberTypeMap") == null || reloadflag) {
            List memberTypeList = dictionaryDao.querySubMemberTypeList("S");
            TreeMap memberTypeMap = convertListtoMap(memberTypeList);
            setAppScopeObj(request, "innerMemberTypeMap", memberTypeMap);
        }

        if (getAppScopeObj(request, "recommendLevelMap") == null || reloadflag) {
            List recommendlevelList = dictionaryDao.queryRecommendlevelList();
            TreeMap recommendLevelMap = convertListtoMap(recommendlevelList);
            setAppScopeObj(request, "recommendLevelMap", recommendLevelMap);
        }

        if (getAppScopeObj(request, "serviceStatusMap") == null || reloadflag) {
            List serviceStatusList = dictionaryDao.queryServiceStatusList();
            TreeMap serviceStatusMap = convertListtoMap(serviceStatusList);
            setAppScopeObj(request, "serviceStatusMap", serviceStatusMap);
        }

        if (getAppScopeObj(request, "serviceUnitMap") == null || reloadflag) {
            List list = dictionaryDao.queryServiceUnitList();
            TreeMap serviceUnitMap = convertListtoMap(list);
            setAppScopeObj(request, "serviceUnitMap", serviceUnitMap);
        }

        if (getAppScopeObj(request, "serviceOrderStatusMap") == null || reloadflag) {
            List list = dictionaryDao.queryServiceOrderStatusList();
            TreeMap serviceOrderStatusMap = convertListtoMap(list);
            setAppScopeObj(request, "serviceOrderStatusMap", serviceOrderStatusMap);
        }

        if (getAppScopeObj(request, "blogStatusMap") == null || reloadflag) {
            List blogStatusList = dictionaryDao.queryBlogStatusList();
            TreeMap blogStatusMap = convertListtoMap(blogStatusList);
            setAppScopeObj(request, "blogStatusMap", blogStatusMap);
        }

        if (getAppScopeObj(request, "contentClassMap") == null || reloadflag) {
            List contentClassList = dictionaryDao.queryContentClassList();
            TreeMap contentClassMap = convertListtoMap(contentClassList);
            setAppScopeObj(request, "contentClassMap", contentClassMap);
        }        
        
        if (getAppScopeObj(request, "blogClassMap") == null || reloadflag) {
            List blogClassList = dictionaryDao.queryBlogClassList();
            TreeMap blogClassMap = convertListtoMap(blogClassList);
            setAppScopeObj(request, "blogClassMap", blogClassMap);
        }

        if (getAppScopeObj(request, "normUserMap") == null || reloadflag) {
            List normUserList = dictionaryDao.queryNormUserList();
            TreeMap normUserMap = convertListtoMap(normUserList);
            setAppScopeObj(request, "normUserMap", normUserMap);
        }

        if (getAppScopeObj(request, "cityMap") == null || reloadflag) {
            List list = dictionaryDao.queryCityList(COUNTRY_CODE);
            TreeMap cityMap = convertListtoMap(list);
            setAppScopeObj(request, "cityMap", cityMap);
        }

        if (getAppScopeObj(request, "languageMap") == null || reloadflag) {
            List languageList = dictionaryDao.queryLanguageList();
            TreeMap languageMap = convertListtoMap(languageList);
            setAppScopeObj(request, "languageMap", languageMap);
        }
        
        if (getAppScopeObj(request, "contactTypeMap") == null || reloadflag) {
            List contactTypeList = dictionaryDao.queryContactTypeList(null);
            TreeMap contactTypeMap = convertListtoMap(contactTypeList);
            setAppScopeObj(request, "contactTypeMap", contactTypeMap);
        }
        if (getAppScopeObj(request, "contactTypeIconMap") == null || reloadflag) {
            List contactTypeIconList = dictionaryDao.queryContactTypeIconList(null);
            TreeMap contactTypeIconMap = convertListtoMap(contactTypeIconList);
            setAppScopeObj(request, "contactTypeIconMap", contactTypeIconMap);
        }
        
        if (getAppScopeObj(request, "phoneContactTypeMap") == null || reloadflag) {
            List phoneContactTypeList = dictionaryDao.queryContactTypeList(ContactClass.PN.name());
            TreeMap phoneContactTypeMap = convertListtoMap(phoneContactTypeList);
            setAppScopeObj(request, "phoneContactTypeMap", phoneContactTypeMap);
        }
        
        if (getAppScopeObj(request, "emailContactTypeMap") == null || reloadflag) {
            List emailContactTypeList = dictionaryDao.queryContactTypeList(ContactClass.EM.name());
            TreeMap emailContactTypeMap = convertListtoMap(emailContactTypeList);
            setAppScopeObj(request, "emailContactTypeMap", emailContactTypeMap);
        }
        
        if (getAppScopeObj(request, "socialContactTypeMap") == null || reloadflag) {
            List socialContactTypeList = dictionaryDao.queryContactTypeList(ContactClass.SC.name());
            TreeMap socialContactTypeMap = convertListtoMap(socialContactTypeList);
            setAppScopeObj(request, "socialContactTypeMap", socialContactTypeMap);
        }
        
        if (getAppScopeObj(request, "orderStatusMap") == null || reloadflag) {
            List list = dictionaryDao.queryServiceOrderStatusList();
            SortedMap orderStatusMap = convertListtoSortedMap(list);
            setAppScopeObj(request, "orderStatusMap", orderStatusMap);
        }
        
        return "Global Dictionary Refreshed";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private TreeMap convertListtoMap(List<Dictionary> list) {
        TreeMap hashmap = new TreeMap();
        for (Dictionary dictionary : list) {
            hashmap.put(dictionary.getCode(), dictionary.getName());
        }
        return hashmap;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private SortedMap convertListtoSortedMap(List<Dictionary> list) {
        SortedMap map = new TreeMap();
        for (Dictionary dictionary : list) {
            map.put(dictionary.getCode(), dictionary.getName());
        }
        return map;
    }
}
