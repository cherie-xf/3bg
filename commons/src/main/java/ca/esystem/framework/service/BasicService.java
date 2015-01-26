package ca.esystem.framework.service;
import java.util.List;

public interface BasicService {
	public List queryList(Object condition);
	  
    public int queryCount(Object condition);
    
    public Object queryOne(Object condition);
    
    public Object add(Object obj);
    
    public int update(Object obj);
    
    public int archive(Object obj);
    
    public int delete(Object obj);
}
