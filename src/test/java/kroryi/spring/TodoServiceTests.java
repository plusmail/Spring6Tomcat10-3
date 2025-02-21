package kroryi.spring;

import kroryi.spring.dto.PageRequestDTO;
import kroryi.spring.dto.PageResponseDTO;
import kroryi.spring.dto.TodoDTO;
import kroryi.spring.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@ExtendWith(SpringExtension.class)
@WebAppConfiguration  // Spring MVC 시뮬레이션 할때 사용
@ComponentScan(basePackages = "kroryi.spring")
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/root-context.xml",
        "file:src/main/webapp/WEB-INF/servlet-context.xml"})
public class TodoServiceTests {

    @Autowired(required = false)
    private TodoService service;

    @Test
    public void testBeanLoad() {
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

    @Test
    public void testPaging(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .build();

        PageResponseDTO<TodoDTO> pageResponseDTO = service.getList(pageRequestDTO);
        log.info(pageResponseDTO.toString());

    }

    @Test
    public void testSearch(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("111")
                .types(new String[]{"t", "w"})
                .build();

        PageResponseDTO<TodoDTO> pageResponseDTO = service.getList(pageRequestDTO);
        log.info(pageResponseDTO.toString());

    }
}
