package kroryi.spring;

import kroryi.spring.dto.TodoDTO;
import kroryi.spring.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {

    @Autowired(required = false)
    private TodoService service;

    @Test
    public void testBeanLoad(){
        assertNotNull(service, "등록 않되어 있음.");
    }

    @Test
    public void testRegister() {
        TodoDTO dto = TodoDTO.builder()
                .title("Test.. job11111111111")
                .dueDate(LocalDate.now())
                .writer("Test01")
                .build();
        service.register(dto);
    }
}
