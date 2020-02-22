package com.rx.demo.constant;


public enum RESOURCES_TYPE {

    /**
     * 公共菜单：可以匿名访问
     */
    PUBLIC_RESOURCES(0),

    /**
     * 一般菜单：登陆访问
     */
    ORDINARY_RESOURCE(1),


    /**
     * 系统菜单：授权访问
     */
    SYSTEM_RESOURCES(2);

    private Integer type;

    RESOURCES_TYPE(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

}


