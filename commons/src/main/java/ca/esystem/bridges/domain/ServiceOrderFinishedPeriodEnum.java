package ca.esystem.bridges.domain;

/**
 * @author Lei
 *
 */
public enum ServiceOrderFinishedPeriodEnum {
    threeMonth(3, "过去三个月"), oneYear(12, "过去一年"), threeYear(36, "过去三年");

    private Integer code;
    private String name;

    private ServiceOrderFinishedPeriodEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ServiceOrderFinishedPeriodEnum getInstance(final Integer code) {

        for (final ServiceOrderFinishedPeriodEnum type : ServiceOrderFinishedPeriodEnum.values()) {
            if (type.code == code) {
                return type;
            }
        }

        throw new IllegalArgumentException(" ServiceOrderFinishedPeriod could not be determined with code [" + code + "]");
    }

    public static ServiceOrderFinishedPeriodEnum getInstanceByName(final String name) {

        for (final ServiceOrderFinishedPeriodEnum type : ServiceOrderFinishedPeriodEnum.values()) {
            if (type.name == name) {
                return type;
            }
        }

        throw new IllegalArgumentException("ServiceOrderFinishedPeriod could not be determined with name [" + name + "]");
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
