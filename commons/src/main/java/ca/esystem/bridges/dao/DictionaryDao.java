package ca.esystem.bridges.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author Larry, Lei, Cherie
 *
 */

@Repository
public interface DictionaryDao {
    public List<?> queryTicketStatusList();

    public List<?> queryTicketSourceType();

    public List<?> queryLanguageList();

    public List<?> queryContactTypeList(String typeClass);
    
    public List<?> queryContactTypeIconList(String typeClass);

    public List<?> queryCountryList();

    public List<?> queryCityList(String country);

    public List<?> queryMemberTypeList();

    public List<?> querySubMemberTypeList(String type_code);

    public List<?> queryRecommendlevelList();

    public List<?> queryServiceUnitList();

    public List<?> queryServiceStatusList();

    public List<?> queryServiceOrderStatusList();

    public List<?> queryBlogStatusList();

    public List<?> queryBlogClassList();
    
    public List<?> queryNormUserList();
    
    public List<?> queryContentClassList();

}
