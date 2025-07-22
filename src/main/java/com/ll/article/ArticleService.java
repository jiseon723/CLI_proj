package com.ll.article;

import com.ll.members.Members;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    ArticleRepository articleRepository;

    ArticleService () {articleRepository = new ArticleRepository();}

    public int create(String subject, String content) {
        return articleRepository.create(subject, content);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article FindById(int id) {return articleRepository.FindById(id);}

    public void remove(Article article) {articleRepository.remove(article);}

    public void change(Article article, String modifySubject, String modifyContent) {
        articleRepository.change(article, modifySubject, modifyContent);
    }

    public int signUp(String userId, String password) {
        return articleRepository.signUp(userId, password);
    }

    public Members logIn(String inputId, String inputPw) {
        return articleRepository.logIn(inputId, inputPw);
    }
}
