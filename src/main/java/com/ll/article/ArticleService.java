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
}
