package com.sourcecode.tinyioc.beans;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class PVs {
    private final List<PV> pvList = new ArrayList<>();
    public PVs() {
    }
    public void addPV(PV pv){
        this.pvList.add(pv);
    }
}
