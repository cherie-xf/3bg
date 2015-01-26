//Source file: D:\\Project\\PPM_Product\\03.Design\\03.05Model\\export\\generate code\\com\\ctvit\\framework\\persistence\\dao\\BasicQueryDao.java

package ca.esystem.framework.dao;

import java.util.List;

public interface BasicQueryDao 
{
   
   /**
    * @param condition
    * @return java.util.List
    * @roseuid 4B94B8FC0157
    */
   public List queryListByCondition(Object condition);
     
   /**
    * @param condition
    * @return Object
    * @roseuid 4B94B9110203
    */
   public Object queryObjectByCondition(Object condition);
   
   /**
    * @param condition
    * @return int
    * @roseuid 4BA86E6C036A
    */
   public int queryCountRowsByCondition(Object condition);
}
