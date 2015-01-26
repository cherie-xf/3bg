package ca.esystem.bridges.domain;

public enum BloodType {
    A("A", "A"), B("B", "B"), O("O", "O"), AB("AB", "AB");
    
    private String    code;
    private String name;

    private BloodType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BloodType getInstance(final String code) {

        for (final BloodType bloodType : BloodType.values()) {
            if (bloodType.code == code) {
                return bloodType;
            }
        }

        throw new IllegalArgumentException("BloodType could not be determined with code [" + code + "]");
    }

    public static BloodType getInstanceByName(final String name) {

        for (final BloodType bloodType : BloodType.values()) {
            if (bloodType.name == name) {
                return bloodType;
            }
        }

        throw new IllegalArgumentException("BloodType could not be determined with name [" + name + "]");
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
