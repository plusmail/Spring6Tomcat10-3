package kroryi.spring.service;


import kroryi.spring.dto.PageRequestDTO;
import kroryi.spring.dto.PageResponseDTO;
import kroryi.spring.dto.TodoDTO;
import kroryi.spring.mapper.TodoMapper;
import kroryi.spring.vo.TodoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoMapper mapper;

    private final ModelMapper modelMapper;

    public void register(TodoDTO dto) {

        log.info(modelMapper);
        log.info("dto--->{}", dto.toString());
        TodoVO vo = modelMapper.map(dto, TodoVO.class);
        log.info(vo);
        mapper.insert(vo);
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        List<TodoVO> voList = mapper.selectList(pageRequestDTO);

        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        int total = mapper.getCount(pageRequestDTO);

        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }

    @Override
    public List<TodoDTO> getAll() {

        List<TodoDTO> dtoList = mapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public TodoDTO getOne(Long tno) {
        return modelMapper.map(mapper.selectOne(tno), TodoDTO.class);
    }

    @Override
    public void remove(Long tno) {
        mapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO dto) {
        TodoVO vo = modelMapper.map(dto, TodoVO.class);
        mapper.update(vo);
    }

}
