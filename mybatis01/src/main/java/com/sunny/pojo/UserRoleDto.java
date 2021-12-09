package com.sunny.pojo;

public class UserRoleDto extends User{

    private String role_name;

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return super.toString()+"UserDto{" +
                "role_name='" + role_name + '\'' +
                "}\n";
    }
}
