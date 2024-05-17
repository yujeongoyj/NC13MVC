package main;

import controller.UserController;
import viewer.UserViewer;

import java.util.Scanner;

public class BoardMain {
    public void main(String[] args){
        // 입력에 사용할 Scanner 클래스 객체
        Scanner scanner = new Scanner(System.in);

        // 각종 컨트롤러 클래스 객체
        UserController userController = new UserController();

        // 각종 뷰어 클래스 객체
        UserViewer userViewer = new UserViewer();

        // setter를 사용한 의존성 주입
        userViewer.setScanner(scanner);
        userViewer.setUserController(userController);
    }
}
