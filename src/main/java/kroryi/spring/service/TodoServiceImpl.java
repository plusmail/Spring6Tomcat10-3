package kroryi.spring.service;


import kroryi.spring.dto.TodoDTO;
import kroryi.spring.mapper.TodoMapper;
import kroryi.spring.vo.TodoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoMapper mapper;

    private final ModelMapper modelMapper;

    public void register(TodoDTO dto) {

        log.info(modelMapper);
        log.info("dto--->{}", dto.toString());
        TodoVO vo = modelMapper.map(dto, TodoVO.class);
        log.info(vo);
        mapper.insert(vo);
    }

}
