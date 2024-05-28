package main;


import java.sql.Connection;
import java.sql.DriverManager;

// MySql 과 자바 연동해보기
public class MySqlMain {
    public static void main(String[] args) {

        // 우리가 메소드를 선언할 때 종종
        // 메소드 이름과 파라미터 뒤에 throws Exception이라고 적어준다
        // 해당 코드의 의미는 이 메소드를 실행할 때 어떠한 오류가 발생할 수 있다
        // 라고 미리 경고를 하는 것이다.
        // 추후에 실제 해당 메소드를 사용할 때에는
        // 아래와 같이 try/ catch 구조를 사용하여
        // 해당 오류가 발생했을 때 어떻게 처리할 것인지를 적어준다.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/board";
            String username = "root";
            String password = "1234";

            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("connection 성공");

        } catch (Exception e) { // 의미: 발생할 수 있는 모든 exception, *보통의 상황에서 nullException과 같이 어떤 에러인지를 명시해야함
            e.printStackTrace(); // 오류가 발생하면 그 오류의 내역을 출력
        } // 이렇듯 예외 상황이 생겼을 때 반드시 어떠한 메소드를 실행할지 작성해야함
    }
}
