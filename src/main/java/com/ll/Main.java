package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lastId = 1;
        List<Article> articleList = new ArrayList<>();

        System.out.println("== 게시판 앱 ==");

        while (true) {
            System.out.print("명령 ) ");
            String command = sc.nextLine().trim();

            if (command.equals("종료")) {
                break;
            } else if (command.equals("등록")) {
                System.out.print("제목 ) ");
                String subject = sc.nextLine();
                System.out.print("내용 ) ");
                String content = sc.nextLine();

                Article article = new Article(lastId, subject, content);
                articleList.add(article);

                System.out.printf("%d번 게시글이 등록되었습니다.\n", lastId);
                lastId++;
            } else if (command.equals("목록")) {
                System.out.println(" 번호 / 제목 / 내용 ");
                System.out.println("=======================");

                for(int i = 0; i < articleList.size(); i++) {
                        Article article = articleList.get(i);
                        System.out.printf(" %d / %s / %s \n", article.getId(), article.getSubject(), article.getContent());
                }
            } else if (command.startsWith("삭제")){
                String[] commandList = command.split("\\?", 2);
                String[] paramsStr = commandList[1].split("=", 2);

                String value = paramsStr[1];
                int idx = Integer.parseInt(value);

                for (int i = 0; i < articleList.size(); i++) {
                    if (articleList.get(i).getId() == idx) {
                        articleList.remove(i);
                    }
                }

                System.out.printf("%d번 게시물이 삭제되었습니다.\n", idx);
            }
        }
        sc.close();
    }
}