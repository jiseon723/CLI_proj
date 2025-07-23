package com.ll.article;

import com.ll.Container;
import com.ll.members.Members;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ll.members.Members.*;

public class ArticleRepository {

    public int create (String subject, String content) {
        String sql = String.format("inselt into article set subject = '%s', content = '%s', regDate = now()", subject, content);
        int id = Container.getDbConnection().insert(sql);

        return id;
    }



    public void remove(Article article) {
        String sql = String.format("DELETE FROM article where id = '%d'", article.getId());
        Container.getDbConnection().delete(sql);
    }

    public void change(Article article, String modifySubject, String modifyContent) {
        String sql = String.format("UPDATE article FROM subject = '%s', content = '%s' WHERE id = '%d'", modifySubject, modifyContent, article.getId());
        Container.getDbConnection().update(sql);
    }

    public int signUp(String userId, String password) {
        String sql = String.format("INSERT INTO members set userId = '%s', password = '%s', regDate = now()", userId, password);
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

    public List<Members> logIn(String id, String userId , String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            conn = (Connection) Container.getDbConnection();
            List<Map<String, Object>> rows = Container.getDbConnection().selectRows("select * from members WHERE userId = ? AND password = ?");
            pstmt = conn.prepareStatement(id);
            pstmt.setString(1, userId);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ;
    }

    public Members findByMember(String id, String inputId, String inputPw) throws SQLException {
        List<Members> membersList = this.logIn(id, getUserId(), getPassword());

        for (Members item : membersList) {
            if (item.getUserId().equals(inputId) && item.getPassword().equals(inputPw)) {
                return item; //로그인 성공
            }
        }

        return null;
    }
}
