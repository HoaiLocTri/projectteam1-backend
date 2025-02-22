package fpt.intern.fa_api.model.response;

import fpt.intern.fa_api.dto.LessonDTO;
import fpt.intern.fa_api.dto.UnitDTO;
import fpt.intern.fa_api.model.enums.DeliveryType;
import fpt.intern.fa_api.model.enums.Method;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SyllabusContentResponse {
    private Long id;
    private Long day;
    private List<UnitDTO> Listunit;
    private String unitName;
    private Long unitNumber;
    private Long syllabusId;
}
