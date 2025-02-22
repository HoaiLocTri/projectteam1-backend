package fpt.intern.fa_api.model.request.AsEntity;

import fpt.intern.fa_api.dto.LessonDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SyllabusContentRequest {
    private Long day;
    private List<LessonDTO> lesson;
    private String unitName;
    private Long unitNumber;
    private Long syllabusId;
}


