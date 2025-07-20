package com.ll.article;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    List<Article> articleList = new ArrayList<>();
    int lastId = 1;

    public int create(String subject, String content) {
        Article article = new Article(lastId, subject, content);
        articleList.add(article);

        lastId++;

        return article.getId();
    }

    public List<Article> findAll() {
        return articleList;
    }

    public Article getFindId(int id) {
        for (Article item : articleList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void remove(Article article) {
        articleList.remove(article);
    }
}
