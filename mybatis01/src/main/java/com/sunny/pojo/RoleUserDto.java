package com.sunny.pojo;

import java.util.List;

public class RoleUserDto extends  Role{

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return super.toString()+"RoleUserDto{" +
                "users=" + users +
                "}\n";
    }
}
