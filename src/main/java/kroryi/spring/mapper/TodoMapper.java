package kroryi.spring.mapper;

import kroryi.spring.vo.TodoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoMapper {


    void insert(TodoVO vo);

}
