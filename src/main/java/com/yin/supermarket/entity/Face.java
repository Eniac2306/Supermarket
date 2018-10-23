package com.yin.supermarket.entity;


import org.springframework.data.annotation.Id;

public class Face {
    private String name;
    private Integer age;
    private String sex;
    private String feature;
    private String id;


    public Face(String name,int age,String sex,String feature){
        this.name = name ;
        this.age  = age  ;
        this.sex  = sex  ;
        this.feature = feature;
    }


    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
