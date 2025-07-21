package com.ll.article;

import java.util.Map;

public class Article {
    int id;
    String subject;
    String content;
    String userId;
    String password;

    public Article (int id, String subject, String content) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.userId = userId;
        this.password = password;
    }

    public Article (Map<String, Object> row) {
        this.id = (int)row.get(id);
        this.subject = (String)row.get(subject);
        this.content = (String)row.get(content);
        this.userId = (String)row.get(userId);
        this.password = (String)row.get(password);
    }

    public int getId () {return this.id;}

    public String getSubject () {return this.subject;}

    public String getContent () {return this.content;}

    public void setSubject (String subject) {this.subject = subject;}

    public void setContent (String content) {this.content = content;}

    public String getUserId () {return this.userId;}

    public String getPassword () {return this.password;}

    public void setUserId (String userId) {this.userId = userId;}

    public void setPassword (String password) {this.password = password;}
}
