package com.ll.article;

import com.ll.Container;
import com.ll.Recuest;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    ArticleService articleService;

    ArticleController () {
        articleService = new ArticleService();
    }

    public void write() {
        System.out.print("제목 ) ");
        String subject = Container.getSc().nextLine();
        System.out.print("내용 ) ");
        String content = Container.getSc().nextLine();

        int id = articleService.create(subject, content);

        System.out.printf("%d번 게시글이 등록되었습니다.\n", id);
    }

    public void list() {
        List<Article> articleList = articleService.findAll();

        System.out.println(" 번호 / 제목 / 내용 ");
        System.out.println("=======================");

        for(int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            System.out.printf(" %d / %s / %s \n", article.getId(), article.getSubject(), article.getContent());
        }
    }

    public void delete(Recuest recuest) {
        int id = _getIntParam(recuest.getParams("id"));
        Article article = _getFindById(id);

        if (id == -1) {
            System.out.printf("잘못된 입력입니다.");
            return;
        }

        if (article == null) {
            System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
        } else {
            articleList.remove(article);
            System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
        }
    }

    public void change(Recuest recuest) {
        int id = _getIntParam(recuest.getParams("id"));
        Article article = _getFindById(id);

        if (id == -1) {
            System.out.printf("잘못된 입력입니다.");
            return;
        }

        if (article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
        } else {
            System.out.printf("제목(기존) : %s\n", article.getSubject());
            System.out.printf("제목 : ");
            String modifySubject = Container.getSc().nextLine();
            article.setSubject(modifySubject);

            System.out.printf("내용(기존) : %s\n", article.getContent());
            System.out.printf("내용 : ");
            String modifyContent = Container.getSc().nextLine();
            article.setContent(modifyContent);

            System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
        }
    }

    private Article _getFindById(int id) {
        for (Article item : articleList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    private int _getIntParam (String id) {
        int defultValue = -1;

        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return defultValue;
        }
    }
}
