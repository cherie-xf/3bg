package ca.esystem.bridges.service;

import java.util.List;

import ca.esystem.bridges.domain.CategoryTree;
import ca.esystem.framework.service.BasicService;

/**
 * Service Interface of Category.
 * 
 * @author Lei
 *
 */
public interface CategoryService extends BasicService {
    public int addTag(Object obj);

    public int deleteTag(Object obj);

    public List<?> queryTagListByCategoryId(Object obj);

    public List queryChildrenList(String parent_id);

    public CategoryTree getChildrenCategoryTree(CategoryTree topCategory);

    public CategoryTree getStaticCategoryTree();

    public void reflush();
}
