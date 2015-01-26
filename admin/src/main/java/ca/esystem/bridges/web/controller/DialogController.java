package ca.esystem.bridges.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.esystem.bridges.domain.CategoryTree;
import ca.esystem.bridges.domain.Membership;
import ca.esystem.bridges.domain.ServiceProduct;
import ca.esystem.bridges.domain.UserAccountSearch;
import ca.esystem.bridges.domain.UserAccountStatusType;
import ca.esystem.bridges.service.CategoryService;
import ca.esystem.bridges.service.MembershipService;
import ca.esystem.bridges.service.ServiceProductMngService;
import ca.esystem.bridges.service.UserAccountService;
import ca.esystem.framework.web.controller.AbstractController;

@Controller
public class DialogController extends AbstractController {
    private final Logger  logger = LoggerFactory.getLogger(this.getClass().getName());
    @Resource(name = "UserAccountService")
    private UserAccountService userAccountService;
    
    @Resource(name = "ServiceProductMngService")
    private ServiceProductMngService serviceProductMngService;
    
    @Resource(name = "MembershipService")
    private MembershipService membershipService;
    
    @Resource(name = "CategoryService")
    private CategoryService categoryService;

    @RequestMapping(value = "/dialog/chooseuser", method = { RequestMethod.GET, RequestMethod.POST})
    public String searchUserAccount(@ModelAttribute("accountQuery") UserAccountSearch accountSearch, Model model, HttpServletRequest request) throws Exception {

        logger.debug("Search user account");
        int rownum = userAccountService.queryCount(accountSearch);
        accountSearch.getPagination().setRowCount(rownum);
        accountSearch.getPagination().setPageSize(20);
        int pagecount = accountSearch.getPagination().getPageCount();
        int currentpage = accountSearch.getPagination().getCurrentPage();
        List pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        accountSearch.setOrderByClause("createdAt desc");
        List accountList = userAccountService.queryList(accountSearch);
        model.addAttribute("accountList", accountList);
        
        Map<Integer, String> statusMap = new HashMap<Integer, String>();
        statusMap.put(UserAccountStatusType.active.getCode(), UserAccountStatusType.active.getName());
        statusMap.put(UserAccountStatusType.inactive.getCode(), UserAccountStatusType.inactive.getName());
        statusMap.put(UserAccountStatusType.locked.getCode(), UserAccountStatusType.locked.getName());

        model.addAttribute("accountStatusMap", statusMap);
        return "/dialog/chooseuser";
    }
    
    @RequestMapping(value = "/dialog/serviceproduct", method = { RequestMethod.GET, RequestMethod.POST})
    public String searchServiceProduct(@ModelAttribute("serviceProductQuery") ServiceProduct serviceProductQuery, Model model, HttpServletRequest request) throws Exception {
        int rownum = serviceProductMngService.queryCount(serviceProductQuery);
        serviceProductQuery.getPagination().setRowCount(rownum);
        serviceProductQuery.getPagination().setPageSize(20);
        int pagecount = serviceProductQuery.getPagination().getPageCount();
        int currentpage = serviceProductQuery.getPagination().getCurrentPage();
        List pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        serviceProductQuery.setOrderByClause("a.service_id desc");
        List list = serviceProductMngService.queryList(serviceProductQuery);
        model.addAttribute("serviceProductList", list);        

        return "/dialog/serviceproduct";
    }
    
    @RequestMapping(value = "/dialog/businessmember", method = { RequestMethod.GET, RequestMethod.POST})
    public String searchServiceProduct(@ModelAttribute("membershipQuery") Membership membershipQuery, Model model, HttpServletRequest request) throws Exception {
        queryMembershipList(membershipQuery, model);

        return "/dialog/businessmember";
    }
    
    private void queryMembershipList(Membership membershipQuery, Model model) {
        int rownum = membershipService.queryCount(membershipQuery);
        membershipQuery.getPagination().setRowCount(rownum);
        membershipQuery.getPagination().setPageSize(20);
        int pagecount = membershipQuery.getPagination().getPageCount();
        int currentpage = membershipQuery.getPagination().getCurrentPage();
        List<?> pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);
        
        if(membershipQuery.getType_code() == null || membershipQuery.getType_code().length() == 0){
            membershipQuery.setType_code("B");
        }

        membershipQuery.setOrderByClause("a.member_id asc");
        List<?> membershiplist = membershipService.queryList(membershipQuery);
        model.addAttribute("membershiplist", membershiplist);
    }
    
    /**
     * 更新分类列表
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/dialog/refreshCategorydialog", method = { RequestMethod.GET })
    public @ResponseBody String refreshCategorydialog( Model model, HttpServletRequest request) throws Exception {

        
        logger.debug("Received refreshCategorydialog request");
        // reflush the categorytree
        categoryService.reflush();
     
        String body = "<script language='javascript'>alert('Update Categorydialog Success!');history.go(-1);</script>";
        /*String body2 = "<script language='javascript'> $(document).ready(function(){";
         body2 += "$('form').before('<div class='modal fade' id='confirm-delete' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'><div class='modal-dialog'><div class='modal-content'><div class='modal-body'><p>update success!</p></div></div> </div> </div>' );";
        body2+="$('#confirm-delete').modal('show');";
        body2+="});</script></body></html>";*/
        
        return body;
    }
}
