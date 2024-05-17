package controller;
// 컨트롤러
// 컨트롤러 클래스는 주로 사용자의 요청을 받아서 데이터를 가공하여
// 다시 사용자에게 보내주는 역할을 맡는다.

// 우리에게 데이터베이스가 있으면 컨트롤러가 데이터베이스로 값을 전송하거나
// 전송받은 값을 가공하여 다시 보내주는 역할을 하지만
// 우리는 데이터베이스가 없으므로
// 어레이리스트를 유사 데이터베이스로 사용하게 된다.

import model.StudentDTO;

import java.util.ArrayList;

public class StudentController {
    // 유사 데이터베이스 역할을 할 ArrayList 필드
    private ArrayList<StudentDTO> list;
    // 다음 입력될 학생의 학생 번호를 저장할 int 필드
    private int nextId;

    public StudentController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    // 1. 사용자로부터 StudentDTO 객체를 전달받아서
    //    list에 추가하는 insert()
    public void insert(StudentDTO s) {
        s.setId(nextId++);
        list.add(s);
    }

    // 2. 현재 리스트 전체를 보내주는
    //    selectAll()
    public ArrayList<StudentDTO> selectAll() {
        return list;
    }

    // 3. 특정 id값을 가진 StudentDTO 객체를 리턴하는
    //    selectOne()
    //    단, 해당 id값이 존재하지 않으면 null을 리턴한다.
    public StudentDTO selectOne(int id) {
        StudentDTO temp = new StudentDTO();
        temp.setId(id);

        if (list.contains(temp)) {
            return list.get(list.indexOf(temp));
        }

        return null;
    }

    // 4. 사용자가 보낸 StudentDTO 객체로
    //    리스트의 특정 요소를 수정하는
    //    update()
    public void update(StudentDTO s) {
        int index = list.indexOf(s);
        list.set(index, s);
    }

    // 5. 특정 id 값을 가진 객체를
    //    list에서 삭제하는
    //    delete()
    public void delete(int id) {
        StudentDTO temp = new StudentDTO();
        temp.setId(id);
        list.remove(temp);
    }

    // 6. 파라미터로 들어온 학생 객체의 총점을 계산 하는 메소드
    public int calculateSum(StudentDTO s) {
        return s.getKorean() + s.getEnglish() + s.getMath();
    }

    // 7. 파라미터로 들어온 학생 객체의 평균을 계산 하는 메소드
    public double calculateAverage(StudentDTO s) {
        return (double) calculateSum(s) / 3;
    }
}













