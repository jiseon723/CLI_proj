package com.ll.article;

import com.ll.Container;
import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    List<Article> articleList = new ArrayList<>();
    int lastId = 1;

    public void write() {
        System.out.print("제목 ) ");
        String subject = Container.getSc().nextLine();
        System.out.print("내용 ) ");
        String content = Container.getSc().nextLine();

        Article article = new Article(lastId, subject, content);
        articleList.add(article);

        System.out.printf("%d번 게시글이 등록되었습니다.\n", lastId);
        lastId++;
    }

    public void list() {
        System.out.println(" 번호 / 제목 / 내용 ");
        System.out.println("=======================");

        for(int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            System.out.printf(" %d / %s / %s \n", article.getId(), article.getSubject(), article.getContent());
        }
    }

    public void delete(String command) {
        String[] commandList = command.split("\\?", 2);
        String[] paramsStr = commandList[1].split("=", 2);

        String value = paramsStr[1];
        int idx = Integer.parseInt(value);

        Article article = _getFindById(idx);

        if (article == null) {
            System.out.printf("%d번 게시물이 존재하지 않습니다.\n", idx);
        } else {
            articleList.remove(article);
            System.out.printf("%d번 게시물이 삭제되었습니다.\n", idx);
        }
    }

    public void change(String command) {
        String[] commandList = command.split("\\?", 2);
        String[] paramsStr = commandList[1].split("=", 2);

        String value = paramsStr[1];
        int idx = Integer.parseInt(value);

        Article article = _getFindById(idx);

        if (article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", idx);
        } else {
            System.out.printf("제목(기존) : %s\n", article.getSubject());
            String modifySubject = Container.getSc().nextLine();
            article.setSubject(modifySubject);
            System.out.printf("내용(기존) : %s\n", article.getContent());
            String modifyContent = Container.getSc().nextLine();
            article.setContent(modifyContent);

            System.out.printf("%d번 게시물이 수정되었습니다.\n", idx);
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
}
