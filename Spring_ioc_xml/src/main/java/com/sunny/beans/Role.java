package com.sunny.beans;

public class Role {

    private String roleName;
    private String roleId;

    public Role() {
        System.out.println("加载 Role");
    }

    public Role(String roleName, String roleId) {
        this.roleName = roleName;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
