package com.sunny.beans;

import org.springframework.beans.factory.annotation.Value;

public class Role {
    @Value("A角色")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
