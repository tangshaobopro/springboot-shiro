package com.example.eums;

public enum LoginType {

    USER_TYPE(1,"userRealm"),ADMIN_TYPE(2,"adminRealm");

    private Integer type;
    private String name;

    LoginType(Integer type,String name){
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getName(Integer type){
        LoginType[] types = values();
        for (LoginType loginType : types) {
            if (loginType.getType() == type) {
                return loginType.getName();
            }
        }
        return "";
    }
}
