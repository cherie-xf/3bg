package ca.esystem.bridges.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.esystem.bridges.domain.CategoryTag;
import ca.esystem.bridges.domain.CategoryTree;
import ca.esystem.bridges.domain.Search_Missed;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.CategoryService;
import ca.esystem.bridges.service.SearchMissedService;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * The controller class for search missed key.
 * 
 * @author Cherie
 *
 */
@Controller
public class SearchMissedMngController extends AbstractController {
    private final Logger        logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "SearchMissedService")
    private SearchMissedService searchMissedService;
    @Resource(name = "CategoryService")
    private CategoryService     categoryService;

    @RequestMapping(value = "/searchmissed/missedkeymng", method = { RequestMethod.GET, RequestMethod.POST })
    public String queryMissedKeyMngList(@ModelAttribute("missedkeyQuery") Search_Missed missedkeyQuery, Model model, HttpServletRequest request)
            throws Exception {

        int rownum = searchMissedService.queryCount(missedkeyQuery);
        missedkeyQuery.getPagination().setRowCount(rownum);
        missedkeyQuery.getPagination().setPageSize(20);
        int pagecount = missedkeyQuery.getPagination().getPageCount();
        int currentpage = missedkeyQuery.getPagination().getCurrentPage();
        List pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        missedkeyQuery.setOrderByClause("searched_at");
        List missedkeymnglist = searchMissedService.queryList(missedkeyQuery);
        model.addAttribute("missedkeymnglist", missedkeymnglist);

        return "/searchmissed/missedkeymng";
    }

    @RequestMapping(value = "/searchmissed/delete", method = RequestMethod.GET)
    public String deleteKeyword(@ModelAttribute("missedkeyQuery") Search_Missed missedkeyQuery, @RequestParam("id") int keywordId, Model model,
            HttpServletRequest request) throws Exception {

        Search_Missed updateKeyword = new Search_Missed();
        updateKeyword.setId(keywordId);

        // update is_deleted to true
        // updateKeyword.setIs_deleted(true);
        // searchMissedService.update(updateKeyword);
        searchMissedService.delete(updateKeyword);

        String message = "删除missedkeyword成功";

        model.addAttribute("message", message);
       // return "redirect:missedkeymng.html";
        return "success";
    }

    @RequestMapping(value = "/searchmissed/addkeywordtotag", method = RequestMethod.GET)
    public String addKeywordtoTagForm(@ModelAttribute("missedkeyQuery") Search_Missed missedkeyQuery, Model model, HttpServletRequest request) throws Exception {
        Search_Missed searchmissed = (Search_Missed) searchMissedService.queryOne(missedkeyQuery);
        System.out.println("keyword=" + searchmissed.getKeyword());
        model.addAttribute("searchmissed", searchmissed);

        // get the category tree
        CategoryTree topCategory = categoryService.getStaticCategoryTree();
        List<CategoryTree> displayList = topCategory.getChildrenList();
        model.addAttribute("displayList", displayList);
        return "/searchmissed/categorydialog";
    }

    @RequestMapping(value = "/searchmissed/addkeyword", method = RequestMethod.POST)
    public @ResponseBody String addKeywordtoTag(Model model, HttpServletRequest request) throws Exception {
        int missedid = Integer.parseInt(request.getParameter("missedid"));
        String missedkey = request.getParameter("missedkey");
        String category_id = request.getParameter("category_id");
        System.out.println("missedid=" + missedid + " missedkey=" + missedkey + " category_id" + category_id);

        LoggedInUser user = getLoggedInUser();
        int user_id = user.getUserid();

        String[] category = category_id.split(",");
        for (int i = 0; i < category.length; i++) {
            System.out.println(i + ":" + category[i]);
            CategoryTag tag = new CategoryTag();
            tag.setTag(missedkey);
            tag.setCategory_id(category[i]);
            tag.setCreated_at(new Date());
            tag.setCreated_by(user_id);
            categoryService.addTag(tag);
        }

        Search_Missed missed = new Search_Missed();
        missed.setId(missedid);
        searchMissedService.delete(missed);

        return "关键字 " + missedkey + " 已经添加到 " + category_id + " 的同义词表";
    }

}
