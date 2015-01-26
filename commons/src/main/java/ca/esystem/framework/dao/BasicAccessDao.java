//Source file: D:\\Project\\PPM_Product\\03.Design\\03.05Model\\export\\generate code\\com\\ctvit\\framework\\persistence\\dao\\BasicAccessDao.java

package ca.esystem.framework.dao;


public interface BasicAccessDao extends BasicQueryDao 
{
   
   /**
    * @param obj
    * @return Object
    * @roseuid 4B94BC5E0203
    */
   public int insert(Object obj);
   
   /**
    * @param obj
    * @return int
    * @roseuid 4B94BC6203D8
    */
   public int update(Object obj);
   
   /**
    * @param obj
    * @return int
    * @roseuid 4B94BC6803B9
    */
   public int delete(Object obj);
   
   /**
    * 
    */
   public int archive(Object obj);
}
