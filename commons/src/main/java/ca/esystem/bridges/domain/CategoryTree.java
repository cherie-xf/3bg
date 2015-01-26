package ca.esystem.bridges.domain;

import java.util.List;

public class CategoryTree extends Category {
    
    private List<CategoryTree> childrenList;

    public CategoryTree() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public CategoryTree(String category_id)
    {
        this.setCategory_id(category_id);
    }

    public List<CategoryTree> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<CategoryTree> childrenList) {
        this.childrenList = childrenList;
    }
    
    

}
