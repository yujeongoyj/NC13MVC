package controller;

import model.UserDTO;

import java.util.ArrayList;

public class UserController {
    // 유사 DB 역할을 할 ArrayList 필드
    private ArrayList<UserDTO> list;
    // 다음 입력될 회원의 번호를 저장할 int 필드
    private int nextId;

    public UserController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void insert(UserDTO userDTO) {
        userDTO.setId(nextId++);
        list.add(userDTO);
    }

    public boolean validateUsername(String username) {
        for (UserDTO u : list) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return false;
            }
        }

        return true;
    }

    public UserDTO selectOne(int id) {
        UserDTO temp = new UserDTO();
        temp.setId(id);
        if (list.contains(temp)) {
            return list.get(list.indexOf(temp));
        }

        return null;
    }

    public void update(UserDTO userDTO) {
        list.set(list.indexOf(userDTO), userDTO);
    }

    public void delete(int id) {
        UserDTO temp = new UserDTO();
        temp.setId(id);

        list.remove(temp);
    }

    public UserDTO auth(String username, String password) {
        for (UserDTO u : list) {
            if (username.equalsIgnoreCase(u.getUsername())) {
                if (password.equals(u.getPassword())) {
                    return u;
                }
            }
        }
        return null;
    }

    public String selectNicknameById(int id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);

        return list.get(list.indexOf(userDTO)).getNickname();
    }
}
