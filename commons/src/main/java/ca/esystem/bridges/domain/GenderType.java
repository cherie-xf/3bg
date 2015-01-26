package ca.esystem.bridges.domain;

public enum GenderType {
    male("M", "男"), female("F", "女");
    
    private String    code;
    private String name;

    private GenderType(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public static GenderType getInstance(final String code) {

        for (final GenderType genderType : GenderType.values()) {
            if (genderType.code == code) {
                return genderType;
            }
        }

        throw new IllegalArgumentException("GenderType could not be determined with code [" + code + "]");
    }

    public static GenderType getInstanceByName(final String name) {

        for (final GenderType genderType : GenderType.values()) {
            if (genderType.name == name) {
                return genderType;
            }
        }

        throw new IllegalArgumentException("GenderType could not be determined with name [" + name + "]");
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }    
}
