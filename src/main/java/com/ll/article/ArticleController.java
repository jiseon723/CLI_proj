package com.ll.article;

import com.ll.Container;
import com.ll.Request;
import com.ll.members.Members;

import java.util.List;

public class ArticleController {
    ArticleService articleService;

    public ArticleController () {articleService = new ArticleService();}

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

    public void delete(Request request) {
        int id = _getIntParam(request.getParams("id"));

        if (id == -1) {
            System.out.printf("잘못된 입력입니다.");
            return;
        }

        Article article = articleService.findById(id);

        if (article == null) {
            System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
        } else {
            articleService.remove(article);
            System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
        }
    }

    public void change(Request request) {
        int id = _getIntParam(request.getParams("id"));

        if (id == -1) {
            System.out.printf("잘못된 입력입니다.\n");
            return;
        }

        Article article = articleService.findById(id);

        if (article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
        } else {
            System.out.printf("제목(기존) : %s\n", article.getSubject());
            System.out.printf("제목 : ");
            String modifySubject = Container.getSc().nextLine();

            System.out.printf("내용(기존) : %s\n", article.getContent());
            System.out.printf("내용 : ");
            String modifyContent = Container.getSc().nextLine();

            articleService.change(article, modifySubject, modifyContent);

            System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
        }
    }

    private int _getIntParam (String id) {
        int defultValue = -1;

        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return defultValue;
        }
    }

    public void memberJoin() {
        System.out.println("== 회원가입 ==");
        System.out.print("아이디 입력 : ");
        String userId = Container.getSc().nextLine().trim();

        if (articleService.isUserIdExists(userId)) {
            System.out.println("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
            return;
        }

        System.out.print("비밀번호 입력 : ");
        String password = Container.getSc().nextLine().trim();

        articleService.memberJoin(userId, password);

        System.out.println("회원가입이 완료되었습니다.");
    }

    public void login() {
        if (Container.loginedUserId != null) {
            System.out.println("⚠ 이미 로그인되어 있습니다. 먼저 로그아웃 해주세요.");
            return;
        }

        System.out.println("== 로그인 ==");
        System.out.print("아이디 : ");
        String inputId = Container.getSc().nextLine().trim();
        System.out.print("비밀번호 : ");
        String inputPw = Container.getSc().nextLine().trim();

        if (articleService.login(userId, password)) {
            Container.loginedUserId = userId;
            System.out.println("로그인이 완료되었습니다.");
        } else {
            System.out.println("로그인이 실패했습니다. 아이디와 비밀번호를 다시 확인해주세요.");
        }
    }

    public void logout() {
        if (Container.loginedUserId == null) {
            System.out.println("⚠ 로그인 상태가 아닙니다.");
            return;
        }

        System.out.println("👋 " + Container.loginedUserId + "님, 로그아웃 되었습니다.");
        Container.loginedUserId = null;
    }
}
