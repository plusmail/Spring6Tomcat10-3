package kroryi.spring.sample;


import kroryi.spring.mapper.TimeMapper;
import kroryi.spring.mapper.TimeMapper2;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class SampleTests {

    @Autowired
    private SampleService service;

    @Autowired
    private DataSource dataSource;

    @Autowired(required = false)
    private TimeMapper timeMapper;

    @Autowired(required = false)
    private TimeMapper2 timeMapper2;

    @Test
    public void testService(){
        log.info(service.toString());
        Assertions.assertNotNull(service);
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        log.info(connection.toString());
        Assertions.assertNotNull(connection);
        connection.close();
    }

    @Test
    public void testGetTime(){
        log.info(timeMapper.getTime());
    }

    @Test
    public void testGetTime2(){
        log.info(timeMapper2.getNow());
    }

}
