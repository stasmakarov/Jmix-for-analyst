package com.company.jmixforanalyst.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum CompanySize implements EnumClass<Integer> {

    HUGE(10),
    SMALL(20);

    private final Integer id;

    CompanySize(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static CompanySize fromId(Integer id) {
        for (CompanySize at : CompanySize.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}