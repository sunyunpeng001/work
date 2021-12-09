package com.sunny.beans;

import org.springframework.beans.factory.annotation.Value;

public class Wife {

    @Value("迪丽热巴")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
