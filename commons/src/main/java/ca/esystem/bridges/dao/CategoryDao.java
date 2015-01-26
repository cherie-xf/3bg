package ca.esystem.bridges.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ca.esystem.bridges.domain.CategoryTree;
import ca.esystem.framework.dao.BasicAccessDao;

/**
 * DAO for the tables of Category
 * 
 * @author Lei Han
 *
 */

@Repository
public interface CategoryDao extends BasicAccessDao {
    public int insertTag(Object obj);

    public int deleteTag(Object obj);

    public List<?> queryTagListByCondition(Object obj);
    
    public List<CategoryTree> queryTreeListByCondition(Object obj);
}