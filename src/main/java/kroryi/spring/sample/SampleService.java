package kroryi.spring.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ToString
@RequiredArgsConstructor
public class SampleService {
    @Autowired
    private final SampleDAO sampleDAO;

//@RequiredArgsConstructor 사용 하던지, 아니면 아래와 같이 생성자를 작성한다.
//    public SampleService(SampleDAO sampleDAO) {
//        this.sampleDAO = sampleDAO;
//    }

}
