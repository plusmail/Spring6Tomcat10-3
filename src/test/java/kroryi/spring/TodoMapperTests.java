package kroryi.spring;


import kroryi.spring.dto.PageRequestDTO;
import kroryi.spring.mapper.TodoMapper;
import kroryi.spring.vo.TodoVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@WebAppConfiguration  // Spring MVC 시뮬레이션 할때 사용
@ComponentScan(basePackages = "kroryi.spring")
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/root-context.xml",
        "file:src/main/webapp/WEB-INF/servlet-context.xml"})
public class TodoMapperTests {

    @Autowired(required = false)
    private TodoMapper mapper;

    @Test
    public void testInsert() {
        TodoVO vo  = TodoVO.builder()
                .title("스프링 테스트 한당111111")
                .dueDate(LocalDate.of(2025,2,19))
                .writer("홍길동")
                .build();

        mapper.insert(vo);
    }

    @Test
    public void testSelectAll(){
        List<TodoVO> voList = mapper.selectAll();
//        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelectOne(){
        TodoVO vo = mapper.selectOne(6L);
        log.info(vo);
    }

    @Test
    public void testSelectList(){

        PageRequestDTO dto = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .build();
        List<TodoVO> voList = mapper.selectList(dto);
//        voList.forEach(vo -> log.info(vo));

    }

    @Test
    public void testSelectSearch(){
        PageRequestDTO reqDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{})
//                .keyword("1111")
                .finished(true)
                .from(LocalDate.of(2025,2,19))
                .to(LocalDate.of(2025,2,28))
                .build();

        List<TodoVO> voList = mapper.selectList(reqDTO);
        voList.forEach(vo -> log.info(vo));
    }


}
