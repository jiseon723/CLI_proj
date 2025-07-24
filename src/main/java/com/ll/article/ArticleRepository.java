package com.ll.article;

import com.ll.Container;
import com.ll.members.Members;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ll.members.Members.*;

public class ArticleRepository {

    public int create (String subject, String content) {
        String sql = String.format("INSERT into article set subject = '%s', content = '%s', regDate = now()", subject, content);
        int id = Container.getDbConnection().insert(sql);

        return id;
    }

    public List<Article> findAll() {
        List<Article> articleList = new ArrayList<>();

        List<Map<String, Object>> rows = Container.getDbConnection().selectRows("SELECT * FROM article");

        for (Map<String, Object> row : rows) {
            Article article = new Article(row);
            articleList.add(article);
        }

        return articleList;
    }

    public Article findById(int id) {
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
        String sql = String.format("UPDATE article SET subject = '%s', content = '%s' WHERE id = '%d'", modifySubject, modifyContent, article.getId());
        Container.getDbConnection().update(sql);
    }

    public int signUp(String userId, String password) {
        String sql = String.format("INSERT INTO members set userId = '%s', password = '%s', regDate = now()", userId, password);
        int id = Container.getDbConnection().insert(sql);

        return id;
    }

    public Members findByMember(String inputId, String inputPw) {
        String sql = "SELECT * FROM members WHERE userId = ? AND password = ?";
        Map<String, Object> row = Container.getDbConnection().selectRow(sql);
        if (row.isEmpty()) {
            return null;
        }
        return new Members(row);
    }

    public boolean isUserIdExists(String userId) {
        String sql = "SELECT COUNT(*) FROM members WHERE userId = ?";
        return Container.getDbConnection().selectRowIntValue(sql, userId) > 0;
    }

    public boolean isValidLogin(String userId, String password) {
        String sql = "SELECT COUNT(*) FROM members WHERE userId = ? AND password = ?";
        return Container.getDbConnection().selectRowIntValue(sql, userId, password) > 0;
    }
}
