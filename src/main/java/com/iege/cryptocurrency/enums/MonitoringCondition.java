package com.iege.cryptocurrency.enums;

public enum MonitoringCondition {
    MORE_THAN("more than"), LESS_THEN("less than");

    String name;

    MonitoringCondition(String name) {
        this.name = name;
    }
}
