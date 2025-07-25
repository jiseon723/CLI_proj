package com.ll.members;

import java.util.Map;

public class Members {
    private int id;
    private String userId;
    private String password;

    public Members (int id, String userId, String password) {
        this.id = id;
        this.userId = userId;
        this.password = password;
    }

    public Members (Map<String, Object> row) {
        this.id = (int)row.get("id");
        this.userId = (String)row.get("userId");
        this.password = (String)row.get("password");
    }

    public int getId () {return this.id;}

    public String getUserId() {return userId;}

    public String getPassword() {return password;}

    public void setUserId (String userId) {this.userId = userId;}

    public void setPassword (String password) {this.password = password;}
}
