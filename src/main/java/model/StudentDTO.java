package model;
// 모델
// 모델은 사용자가 입력한 값을 자바 객채의 형태로 바꾸거나
// 아니면 데이터 베이스로부터 받은 값을 자바 객체의 형태로 바꿀 때
// 사용되는 클래스의 종류이다.
// 모델 클래스의 경우 이름의 끝에
// DTO 혹은 VO 가 붙는데
// 각각 Data Transfer Object 혹은 Value Object의 의미를 가진다.
// DTO가 좀더 넓은 의미를 가지므로 DTO를 붙이면 왠만해서는 틀리지 않다.

// 모델 클래스에는 해당 데이터를 객체화 시킬때 필요한 필드, 겟터/셋터, equals()
// 정도의 메소드만 들어간다.

import lombok.Data;
// lombok은 게터/세터, toString() 등 모델 클래스들에게 필요한
// 기본 메소드를 자동으로 생성해주는 외부 라이브러리다.
// 필요에 따라서는 우리가 직접 @Getter, @Setter, 등 필요한 것을
// 골라서 쓸수도 있지만 @Data 라고 적어주면 모든 기능을 다 활성화시킨다.

@Data
public class StudentDTO {
    private int id;
    private String name;
    private int korean;
    private int english;
    private int math;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof StudentDTO) {
            StudentDTO s = (StudentDTO) o;
            return id == s.id;
        }

        return false;
    }

}
