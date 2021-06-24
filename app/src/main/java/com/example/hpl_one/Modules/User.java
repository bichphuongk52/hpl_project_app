package com.example.hpl_one.Modules;

public class User {
    private String ssid, username, roles;

    public User(String ssid, String username, String roles) {
        this.ssid = ssid;
        this.username = username;
        this.roles = roles;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
