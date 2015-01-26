package ca.esystem.bridges.domain;

public enum UserAccountStatusType {
    inactive(0, "未激活"), active(1, "激活"), locked(2, "锁死");

    private int    code;
    private String name;

    private UserAccountStatusType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserAccountStatusType getInstance(final int code) {

        for (final UserAccountStatusType userStatusType : UserAccountStatusType.values()) {
            if (userStatusType.code == code) {
                return userStatusType;
            }
        }

        throw new IllegalArgumentException("UserAccountStatusType could not be determined with code [" + code + "]");
    }

    public static UserAccountStatusType getInstance(final String name) {

        for (final UserAccountStatusType userStatusType : UserAccountStatusType.values()) {
            if (userStatusType.name == name) {
                return userStatusType;
            }
        }

        throw new IllegalArgumentException("UserAccountStatusType could not be determined with name [" + name + "]");
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
