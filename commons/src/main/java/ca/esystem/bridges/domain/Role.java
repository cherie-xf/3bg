package ca.esystem.bridges.domain;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = -5391179254919621996L;

    private int               id;
    private String            roleName;
    private String            description;

    public Role() {

    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
