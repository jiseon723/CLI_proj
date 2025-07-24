package com.ll;

import com.ll.article.ArticleController;
import com.ll.db.DBConnection;
import com.ll.system.SystemController;

public class App {
    ArticleController articleController;
    SystemController systemController;

    public App () {
        DBConnection.DB_NAME = "proj1";
        DBConnection.DB_PORT = 3306;
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";

        Container.getDbConnection().connect();

        articleController = new ArticleController();
        systemController = new SystemController();
    }

    void run () {
        System.out.println("== 게시판 앱 ==");
        System.out.println("== 명령어: 회원가입 / 로그인 / 등록 / 목록 / 삭제 / 수정 / 종료 ==");

        while (true) {
            System.out.print("명령 ) ");
            String command = Container.getSc().nextLine().trim();
            Request request = new Request(command);

            if (request.getActionCode().equals("종료")) {
                systemController.exit();
                break;

            } else if (request.getActionCode().equals("등록")) {
                articleController.write();

            } else if (request.getActionCode().equals("목록")) {
                articleController.list();

            } else if (request.getActionCode().startsWith("삭제")){
                articleController.delete(request);

            } else if (request.getActionCode().startsWith("수정")) {
                articleController.change(request);

            } else if (request.getActionCode().equals("회원가입")) {
                articleController.memberJoin();

            } else if (request.getActionCode().equals("로그인")) {
                articleController.login();

            } else if (request.getActionCode().equals("로그아웃")) {
                articleController.logout();
            }

        }
    }
}
