package controller;

import model.BoardDTO;

import java.util.ArrayList;

public class BoardController {
    private ArrayList<BoardDTO> list;
    private int nextId;

    public BoardController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void insert(BoardDTO boardDTO) {
        boardDTO.setId(nextId++);
        list.add(boardDTO);
    }

    public BoardDTO selectOne(int id) {
        BoardDTO temp = new BoardDTO();
        temp.setId(id);

        return list.get(list.indexOf(temp));
    }

    public ArrayList<BoardDTO> selectAll() {
        return list;
    }

    public void update(BoardDTO boardDTO) {
        list.set(list.indexOf(boardDTO), boardDTO);
    }

    public void delete(int id) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(id);
        list.remove(boardDTO);
    }

    public boolean validateInput(int input) {
        if (input == 0) {
            return true;
        }

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(input);

        return list.contains(boardDTO);
    }
}
















