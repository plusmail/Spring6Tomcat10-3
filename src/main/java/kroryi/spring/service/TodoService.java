package kroryi.spring.service;


import kroryi.spring.dto.PageRequestDTO;
import kroryi.spring.dto.PageResponseDTO;
import kroryi.spring.dto.TodoDTO;

import java.util.List;

public interface TodoService {


    void register(TodoDTO dto);
    List<TodoDTO> getAll();

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);


    TodoDTO getOne(Long tno);
    void remove(Long tno);
    void modify(TodoDTO dto);
}
