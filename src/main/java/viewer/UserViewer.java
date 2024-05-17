package viewer;

import controller.UserController;
import lombok.Getter;
import lombok.Setter;
import model.UserDTO;
import util.ScannerUtil;

import java.util.Scanner;


public class UserViewer {


    @Setter
    private UserDTO logIn;
    @Setter
    private UserController userController;
    @Setter
    private Scanner scanner;

    public void showIndex() {
        String message = "1. 로그인 2. 회원가입 3.프로그램 종류";
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                auth();

                if(logIn != null){
                    // 회원메뉴 실행
                }
            } else if (userChoice == 2) {
                register();
                    // 0516숙제로 구현해오기
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다");
                break;
            }
        }
    }

    private void register(){

    }

    private void auth() {
        String message;
        message = "아이디를 입력하세요";
        String username = ScannerUtil.nextLine(scanner, message);

        message = "비밀번호를 입력하세요";
        String password = ScannerUtil.nextLine(scanner, message);

        UserDTO logIn = userController.auth(username, password);

        if(logIn == null){
            System.out.println("잘못 입력하셨습니다. 로그인 정보를 다시 확인해주세요");
        }
    }
}
