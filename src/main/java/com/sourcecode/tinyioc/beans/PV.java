package com.sourcecode.tinyioc.beans;

import lombok.Getter;

@Getter
public class PV {
    private final String name;
    private final Object value;

    public PV(String name, Object value) {
        this.name = name;
        this.value = value;
    }
}
