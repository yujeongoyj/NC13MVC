package main;


import model.BoardDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

            // 1. 현재 board 테이블의 내용을 전부 뽑아서
            // 어레이리스트 객체에 추가하고 리스트를 출력
            ArrayList<BoardDTO> list = new ArrayList<>();
            String query = "SELECT * FROM board";

            // 위에서 만든 Connection 객체를 통해서 쿼리를 보낼때에는
            // 우리가 PreparedStatement 객체를 통해서 스트링 객체를 보내게 된다
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Select 쿼리와 같이 Preparement의 결과가 존재하는 경우,
            // 우리는 그 결과를 ResultSet에 담게 된다.
            ResultSet resultSet = preparedStatement.executeQuery();

            // resultSet은 우리가 결과를 추출하기 위해서는
            // 반드시 resultSet.next() 라는 것을 통하여
            // 커서의 위치를 while문을 통해서 옮긴다.
            while (resultSet.next()) {
                BoardDTO boardDTO = new BoardDTO();
                //ResultSet 객체의 현재 위치의 값을 꺼낼 때에는
                // 데이터 나입에 맞추어서 다음 메소드를 실행하면 된다
                boardDTO.setId(resultSet.getInt("id"));
                boardDTO.setTitle(resultSet.getString("title"));
                boardDTO.setContent(resultSet.getString("content"));
                boardDTO.setWriterId(resultSet.getInt("writer_id"));
                boardDTO.setEntryDate(resultSet.getTimestamp("entry_date"));
                boardDTO.setModifyDate(resultSet.getTimestamp("modify_date"));

                list.add(boardDTO);
            }

            for (BoardDTO b : list) {
                System.out.println(b);
            }

            // 2. 특정 ID 값을 가진 로우 하나를 불러오기
            // 만약 우리가 어떠한 값을 쿼리에 넣어야 하는 경우, 해당 자리에 ?를 넣어준다
            query = "SELECT * FROM board WHERE id =?";

            // PreparedStatement 를 준비하자
            preparedStatement = connection.prepareStatement(query);
            // ?자리에 우리가 원하는 값을 넣어주자
            // 이때에는 데이터타입에 맞추어 메소드를 불러주면 된다.

            // 1번 자리의 물음표를 3번 넣기
            int index = 1;
            preparedStatement.setInt(index, 3);

            // 위의 쿼리를 실행시킨 값을 resultSet에 저장
            resultSet = preparedStatement.executeQuery();

            // while문을 통해 쿼리의 결과값을 BoardDTO 객체에 저장
            BoardDTO temp = new BoardDTO();
            while (resultSet.next()) {
                temp.setId(resultSet.getInt("id"));
                temp.setTitle(resultSet.getString("title"));
                temp.setContent(resultSet.getString("content"));
                temp.setWriterId(resultSet.getInt("writer_id"));

            }
            System.out.println(temp);

            // 3. BoardDTO 객체에 값을 넣고 해당 객체의 값을
            // 데이터베이스로 넣어보자
            query = "INSERT INTO board (title, content, writer_id) VALUES(?,?,?)";

            preparedStatement = connection.prepareStatement(query);
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setTitle("지금 시간은 11시 10분");
            boardDTO.setContent("학생들에게 예제를 내주기 좋은 시간이다");
            boardDTO.setWriterId(1);

            preparedStatement.setString(1, boardDTO.getTitle());
            preparedStatement.setString(2, boardDTO.getContent());
            preparedStatement.setInt(3, boardDTO.getWriterId());

            // insert, update, delete 와 같이 데이터를 입력/수정/삭제하는 쿼리는
            // executeUpdate() 를 한다.
            preparedStatement.executeUpdate();

            // 4. (강사 기준) 4번 글의 내용을 수정해보자
            BoardDTO boardDT2 = new BoardDTO();
            boardDT2.setId(4);
            boardDT2.setTitle("수전된 제목 4번글");
            boardDT2.setContent("이 4번글은 수정되었습니다. ");

            query = "UPDATE board SET title = ?, content = ?, modify_date = NOW() WHERE id =?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, boardDT2.getTitle()); // parameterIndex는 '?'자리를 식별하는 순서번호
            preparedStatement.setString(2, boardDT2.getContent());
            preparedStatement.setInt(3, boardDT2.getId());

            //preparedStatement.executeUpdate();

            // 5. 삭제하기
            int id =2;
            query = "delete from board where id =?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();



        } catch (Exception e) { // 의미: 발생할 수 있는 모든 exception, *보통의 상황에서 nullException과 같이 어떤 에러인지를 명시해야함
            e.printStackTrace(); // 오류가 발생하면 그 오류의 내역을 출력
        } // 이렇듯 예외 상황이 생겼을 때 반드시 어떠한 메소드를 실행할지 작성해야함
    }
}
