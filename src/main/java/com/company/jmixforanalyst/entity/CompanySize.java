package com.company.jmixforanalyst.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum CompanySize implements EnumClass<String> {

    LARGE("Large"),
    SMALL("Small");

    private final String id;

    CompanySize(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static CompanySize fromId(String id) {
        for (CompanySize at : CompanySize.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}