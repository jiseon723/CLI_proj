package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lastId = 1;

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

                System.out.printf("%d번 게시글이 등록되었습니다.\n", lastId);
                lastId++;
            }
        }
        sc.close();
    }
}