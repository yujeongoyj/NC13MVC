package viewer;

// 실제 사용자가 보게될 UI를 담당할 Viewer
// 단, 우리가 자바 콘솔에서 화면을 보기 때문에 Viewer 클래스가 있는 것이지
// 웹 프로그래밍에서는 웹페이지가 Viewer 가 된다.

import controller.StudentController;
import model.StudentDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentViewer {
    private final Scanner SCANNER = new Scanner(System.in);
    private StudentController studentController;

    public StudentViewer(StudentController studentController) {
        this.studentController = studentController;
    }

    public void showMenu() {
        String message = "1. 입력 2. 출력 3. 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                insertStudent();
            } else if (userChoice == 2) {
                printList();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void insertStudent() {
        StudentDTO s = new StudentDTO();

        String message;

        message = "학생의 이름을 입력해주세요.";
        s.setName(ScannerUtil.nextLine(SCANNER, message));

        message = "학생의 국어 점수를 입력해주세요.";
        s.setKorean(ScannerUtil.nextInt(SCANNER, message));

        message = "학생의 영어 점수를 입력해주세요.";
        s.setEnglish(ScannerUtil.nextInt(SCANNER, message));

        message = "학생의 수학 점수를 입력해주세요.";
        s.setMath(ScannerUtil.nextInt(SCANNER, message));

        studentController.insert(s);
    }

    private void printList() {
        ArrayList<StudentDTO> list = studentController.selectAll();
        if (list.isEmpty()) {
            System.out.println("아직 등록된 학생 정보가 없습니다.");
        } else {
            briefList(list);
            String message = "상세보기할 학생의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            while (!validateValue(userChoice)) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
            }

            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private void briefList(ArrayList<StudentDTO> list) {
        for (StudentDTO s : list) {
            System.out.printf("%d. %s\n", s.getId(), s.getName());
        }
    }

    private boolean validateValue(int value) {
        if (value == 0) {
            return true;
        }

        return studentController.selectOne(value) != null;
    }

    private void printOne(int id) {
        StudentDTO s = studentController.selectOne(id);
        System.out.println("==============================");
        System.out.println(s.getName() + " 학생의 정보");
        System.out.println("------------------------------");
        System.out.printf("번호: %02d번 이름: %s\n", s.getId(), s.getName());
        System.out.printf("국어: %03d점 영어: %03d점 수학: %03d점\n",
                s.getKorean(), s.getEnglish(), s.getMath());
        System.out.printf("총점: %03d점 평균: %06.2f점\n",
                studentController.calculateSum(s), studentController.calculateAverage(s));
        System.out.println("===============================");
        String message = "1. 수정 2. 삭제 3. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);
        if (userChoice == 1) {
            update(id);
        } else if (userChoice == 2) {
            delete(id);
        } else if (userChoice == 3) {
            printList();
        }
    }

    private void update(int id) {
        StudentDTO s = studentController.selectOne(id);
        String message;

        message = "새로운 이름을 입력해주세요.";
        s.setName(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 국어 점수를 입력해주세요.";
        s.setKorean(ScannerUtil.nextInt(SCANNER, message));

        message = "새로운 영어 점수를 입력해주세요.";
        s.setEnglish(ScannerUtil.nextInt(SCANNER, message));

        message = "새로운 수학 점수를 입력해주세요.";
        s.setMath(ScannerUtil.nextInt(SCANNER, message));

        studentController.update(s);
        printOne(id);
    }

    private void delete(int id) {
        String answer = ScannerUtil.nextLine(SCANNER, "정말로 삭제하시겠습니까? Y/N");
        if (answer.equalsIgnoreCase("Y")) {
            studentController.delete(id);
            printList();
        } else {
            printOne(id);
        }
    }

}
















