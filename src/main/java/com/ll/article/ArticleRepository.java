package com.ll.article;

import com.ll.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleRepository {
    List<Article> articleList = new ArrayList<>();
    int lastId = 1;

    public int create (String subject, String content) {
        String sql = String.format("inselt into article set subject = '%s', content = '%s'", subject, content);
        int id = Container.getDbConnection().insert(sql);

        return id;
    }

    public List<Article> findAll() {
        List<Article> articleList = new ArrayList<>();

        List<Map<String, Object>> rows = Container.getDbConnection().selectRows("select * from article");

        for (Map<String, Object> row : rows) {
            Article article = new Article(row);
            articleList.add(article);
        }

        return articleList;
    }

    public Article FindById(int id) {
        List<Article> articleList = this.findAll();

        for (Article item : articleList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void remove(Article article) {
        String sql = String.format("DELETE FROM article where id = '%d'", article.getId());
        Container.getDbConnection().delete(sql);
    }

    public void change(Article article, String modifySubject, String modifyContent) {
        String sql = String.format("UPDATE article FROM subject = '%s', content = '%s' WHERE id = '%d'", modifySubject, modifyContent, article.getId());
        Container.getDbConnection().update(sql);
    }
}
