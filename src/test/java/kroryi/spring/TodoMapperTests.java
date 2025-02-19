package kroryi.spring;


import kroryi.spring.mapper.TodoMapper;
import kroryi.spring.vo.TodoVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
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


}
