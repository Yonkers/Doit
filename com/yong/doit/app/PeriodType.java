package com.yong.doit.app;

public enum PeriodType {

    DAY("天", 1, Cst.DAY), WEEK("周", 7, Cst.WEEK), MONTH("月", 30, Cst.MONTH);

    private String value;
    private int day;

    private int typeId;

    PeriodType(String name, int day, int typeId) {
        this.value = name;
        this.day = day;
        this.typeId = typeId;
    }

    public int getDay() {
        return day;
    }

    public int getTypeId() {
        return typeId;
    }

    @Override
    public String toString() {
        return value;
    }

}
