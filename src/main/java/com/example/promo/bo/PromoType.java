package com.example.promo.bo;

public enum PromoType {
    IND("IND"),
    COMBO("COMBO"),
    PERCENTAGE("PERCENTAGE");

    private String desc;
    private PromoType(String desc){
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
