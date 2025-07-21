package com.ll.article;

import java.util.Map;

public class Article {
    int id;
    String subject;
    String content;

    public Article (int id, String subject, String content) {
        this.id = id;
        this.subject = subject;
        this.content = content;
    }

    public  Article (Map<String, Object> row) {
        this.id = (int)row.get(id);
        this.subject = (String)row.get(subject);
        this.content = (String)row.get(content);
    }

    public int getId () {
        return this.id;
    }

    public String getSubject () {
        return this.subject;
    }

    public String getContent () {
        return this.content;
    }

    public void setSubject (String subject) {
        this.subject = subject;
    }

    public void setContent (String content) {
        this.content = content;
    }
}
