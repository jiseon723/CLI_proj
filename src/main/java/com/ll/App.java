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

        DBConnection DBConnection = new DBConnection();
        DBConnection.connect();

        articleController = new ArticleController();
        systemController = new SystemController();
    }

    void run () {
        System.out.println("== 게시판 앱 ==");

        while (true) {
            System.out.print("명령 ) ");
            String command = Container.getSc().nextLine().trim();
            Recuest recuest = new Recuest(command);

            if (recuest.getActionCode().equals("종료")) {
                systemController.exit();
                break;

            } else if (recuest.getActionCode().equals("등록")) {
                articleController.write();

            } else if (recuest.getActionCode().equals("목록")) {
                articleController.list();

            } else if (recuest.getActionCode().startsWith("삭제")){
                articleController.delete(recuest);

            } else if (recuest.getActionCode().startsWith("수정")) {
                articleController.change(recuest);

            }

        }
    }
}
