package kroryi.spring.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    // ~~~.builder
    //    .size(1)
    //    .build()
    // 이렇게 사용시 page 필드에 1이 초기값을 갖지 못할 수 있다.
    // 그래서 @Builder.Default를 사용하면 page=1의 값을 가지도록 보장 한다.
    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 5)
    @Max(value = 100)
    @Positive
    private int size = 10;


    public int getSkip(){
        return (page - 1) * size;
    }


}
