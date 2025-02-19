package kroryi.spring;

import kroryi.spring.dto.TodoDTO;
import kroryi.spring.vo.TodoVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/root-context.xml"})
public class ModelMapperTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testModelMapperBean() {
        assertNotNull(modelMapper, "ModelMapper가 빈으로 등록되지 않았습니다.");

        // DTO 객체 생성
        TodoDTO dto = TodoDTO.builder()
                .title("Test Title")
                .dueDate(LocalDate.now())
                .writer("Test Writer")
                .build();

        // DTO -> VO 변환
        TodoVO vo = modelMapper.map(dto, TodoVO.class);

        // 변환 검증
        assertEquals(dto.getTitle(), vo.getTitle());
        assertEquals(dto.getDueDate(), vo.getDueDate());
        assertEquals(dto.getWriter(), vo.getWriter());

        System.out.println("DTO -> VO 변환 성공: " + vo);
    }
}
