package ca.esystem.bridges.domain;

import ca.esystem.framework.domain.BaseForm;

public class UserAccountOther extends BaseForm {

    private static final long serialVersionUID = 5673465085642167468L;

    private int               id;
    private String            profession;
    private String            health;
    private String            familyInfo;
    private String            carInfo;
    private String            petInfo;
    private String            propertyInfo;

    public UserAccountOther() {

    }

    public UserAccountOther(UserProfile userProfile) {
        this.id = userProfile.getUserId();
        this.profession = userProfile.getProfession();
        this.health = userProfile.getHealth();
        this.familyInfo = userProfile.getFamilyInfo();
        this.carInfo = userProfile.getCarInfo();
        this.petInfo = userProfile.getPetInfo();
        this.propertyInfo = userProfile.getPropertyInfo();
    }

    public void copyToUserProfile(UserProfile userProfile) {
        userProfile.setProfession(profession);
        userProfile.setHealth(health);
        userProfile.setFamilyInfo(familyInfo);
        userProfile.setCarInfo(carInfo);
        userProfile.setPetInfo(petInfo);
        userProfile.setPropertyInfo(propertyInfo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getFamilyInfo() {
        return familyInfo;
    }

    public void setFamilyInfo(String familyInfo) {
        this.familyInfo = familyInfo;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    public String getPetInfo() {
        return petInfo;
    }

    public void setPetInfo(String petInfo) {
        this.petInfo = petInfo;
    }

    public String getPropertyInfo() {
        return propertyInfo;
    }

    public void setPropertyInfo(String propertyInfo) {
        this.propertyInfo = propertyInfo;
    }

}
