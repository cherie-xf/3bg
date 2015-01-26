package ca.esystem.bridges.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.esystem.bridges.service.CategoryService;
import ca.esystem.framework.service.AbstractService;
import ca.esystem.bridges.dao.CategoryDao;
import ca.esystem.bridges.domain.CategoryTree;

/**
 * Implementation for CategoryService.
 * 
 * @author Lei
 *
 */
@Service("CategoryService")
public class CategoryServiceImpl extends AbstractService implements CategoryService {

    @Resource
    private CategoryDao        categoryDao;

    public static CategoryTree categoryStaticWithTree;

    public List<?> queryList(Object condition) {
        return categoryDao.queryListByCondition(condition);
    }

    public int queryCount(Object condition) {
        return categoryDao.queryCountRowsByCondition(condition);
    }

    public Object queryOne(Object condition) {
        return categoryDao.queryObjectByCondition(condition);
    }

    @Transactional
    public Object add(Object obj) {
        return categoryDao.insert(obj);
    }

    @Transactional
    public int update(Object obj) {
        return categoryDao.update(obj);
    }

    public int archive(Object obj) {
        return 0;
    }

    @Transactional
    public int delete(Object obj) {
        return categoryDao.delete(obj);
    }

    @Transactional
    public int addTag(Object obj) {
        return categoryDao.insertTag(obj);
    }

    @Transactional
    public int deleteTag(Object obj) {
        return categoryDao.deleteTag(obj);
    }

    public List<?> queryTagListByCategoryId(Object obj) {
        return categoryDao.queryTagListByCondition(obj);
    }

    public List queryChildrenList(String parent_id) {
        CategoryTree query = new CategoryTree();
        query.setParent_id(parent_id);
        query.setOrderByClause("a.priority asc, a.category_id asc");
        List<CategoryTree> childrenList = categoryDao.queryTreeListByCondition(query);

        return childrenList;
    }

    public CategoryTree getChildrenCategoryTree(CategoryTree topCategory) {
        List<CategoryTree> childrenlist = queryChildrenList(topCategory.getCategory_id());
        topCategory.setChildrenList(childrenlist);
        for (int i = 0; i < childrenlist.size(); i++) {
            CategoryTree childCategory = (CategoryTree) childrenlist.get(i);
            if (!childCategory.getLeaf()) {
                childCategory = getChildrenCategoryTree(childCategory);
            }
        }
        return topCategory;
    }

    public CategoryTree getStaticCategoryTree() {
        CategoryTree topTree = new CategoryTree("");
        if (categoryStaticWithTree == null) {
            topTree = getChildrenCategoryTree(topTree);
            categoryStaticWithTree = topTree;
        } else {
            topTree = categoryStaticWithTree;
        }
        return topTree;
    }

    public void reflush() {
        categoryStaticWithTree = null;
        getStaticCategoryTree();
    }
}
