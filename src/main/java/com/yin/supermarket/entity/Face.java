package com.yin.supermarket.entity;

import org.springframework.data.annotation.Id;

public class Face {
    @Id
    private String id;
    private String name;
    private String feature;
    private String id_num;
    private String filename;
    private String psw;
    private Double money;

    public Face(String name, String id_num, String psw, String feature,String filename) {
        this.name = name;
        this.feature = feature;
        this.id_num = id_num;
        this.psw = psw;
        this.filename = filename;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeature() {
        return this.feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getId_num() {
        return this.id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public Double getMoney() {
        return this.money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getPsw() {
        return this.psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
