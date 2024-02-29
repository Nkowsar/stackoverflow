package stackOverFlow.security.dtos;

import lombok.Data;

import java.util.List;

@Data
public class QuestionSearchResponseDto {
    private List<QuestionDto> questionDtoList;
    private Integer pageNumber;
    private Integer totalpages;
}
