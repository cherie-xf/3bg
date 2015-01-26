//Source file: D:\\PROJECT\\CNTV_PRODUCT\\04.Develop\\04.02Src\\vms\\src\\com\\ctvit\\framework\\form\\PageForm.java

package ca.esystem.framework.domain;


public class Pagination implements java.io.Serializable
{
   private Integer rowCount = 0;
   private Integer currentPage = 1;
   private Integer pageCount = 1;
   private Integer pageSize = 100;
   private Integer skip=0;
   
   /**
    * @roseuid 4CA056A20374
    */
   public Pagination() 
   {
    
   }

public Integer getRowCount() {
	return rowCount;
}

public void setRowCount(Integer rowCount) {
	if(rowCount<0){
		rowCount = 0;
	}
	this.rowCount = rowCount;
}

public Integer getCurrentPage() {
	return currentPage;
}

public void setCurrentPage(Integer currentPage) {
	if(currentPage<=0){
		currentPage = 1;
	}
	this.currentPage = currentPage;
}

public Integer getPageCount() {
	int pc = rowCount/pageSize;
	if(rowCount%pageSize>0||rowCount==0){
		pc++;
	}
	pageCount=pc;
	return pageCount;
}

public void setPageCount(Integer pageCount) {
	this.pageCount = pageCount;
}

public Integer getPageSize() {
	return pageSize;
}

public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
}

public Integer getSkip() {
	getPageCount();
	skip = (currentPage-1)*getPageSize();
	return skip;
}

public void setSkip(Integer skip) {
	this.skip = skip;
}
   
   
}
