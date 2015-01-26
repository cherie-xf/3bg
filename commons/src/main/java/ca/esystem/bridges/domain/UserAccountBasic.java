package ca.esystem.bridges.domain;

import java.util.Calendar;
import java.util.Date;

import ca.esystem.framework.domain.BaseForm;

public class UserAccountBasic extends BaseForm {

    private static final long serialVersionUID = 5673465085642167468L;

    private int               id;
    private String            email;
    private String            phone;
    private String            firstName;
    private String            lastName;
    private String            preferredName;
    private int               status;
    private String            gender;
    private Date              birthday;
    private String            bloodType;
    private String            preferredLanguage;
    private String            passportNumber;
    private String            chineseIdNumber;
    private String            driverLicense;
    private Integer           yearOfBirth;
    private Integer           monthOfBirth;
    private Integer           dayOfBirth;

    public UserAccountBasic() {

    }

    public UserAccountBasic(User user, UserProfile userProfile) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.preferredName = user.getPreferredName();
        this.status = user.getStatus();
        this.gender = userProfile.getGender();
        this.birthday = userProfile.getBirthday();
        this.bloodType = userProfile.getBloodType();
        this.preferredLanguage = userProfile.getPreferredLanguage();
        this.passportNumber = userProfile.getPassportNumber();
        this.chineseIdNumber = userProfile.getChineseIdNumber();
        this.driverLicense = userProfile.getDriverLicense();
        if (birthday != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(birthday);
            this.yearOfBirth = cal.get(Calendar.YEAR);
            this.monthOfBirth = cal.get(Calendar.MONTH) + 1;
            this.dayOfBirth = cal.get(Calendar.DAY_OF_MONTH);
        }
    }

    public void copyToUser(User user) {
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setPreferredName(this.preferredName);
        user.setStatus(this.status);
    }

    public void copyToUserProfile(UserProfile userProfile) {
        userProfile.setGender(this.gender);
        if (this.yearOfBirth != null && this.monthOfBirth != null && this.dayOfBirth != null) {
            Calendar cal = Calendar.getInstance();
            cal.set(this.yearOfBirth,  this.monthOfBirth - 1, this.dayOfBirth, 0, 0);
            userProfile.setBirthday(cal.getTime());
        }
        userProfile.setBloodType(this.bloodType);
        userProfile.setPreferredLanguage(this.preferredLanguage);
        userProfile.setPassportNumber(this.passportNumber);
        userProfile.setChineseIdNumber(this.chineseIdNumber);
        userProfile.setDriverLicense(this.driverLicense);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        if (birthday != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(birthday);
            this.yearOfBirth = cal.get(Calendar.YEAR);
            this.monthOfBirth = cal.get(Calendar.MONTH) + 1;
            this.dayOfBirth = cal.get(Calendar.DAY_OF_MONTH);
        }
    }
    
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getChineseIdNumber() {
        return chineseIdNumber;
    }

    public void setChineseIdNumber(String chineseIdNumber) {
        this.chineseIdNumber = chineseIdNumber;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(Integer monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public Integer getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Integer dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
}
