package fpt.intern.fa_api.dto;

import fpt.intern.fa_api.model.enums.DeliveryType;
import fpt.intern.fa_api.model.enums.Method;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitDTO {
    private String UnitName;
    private Long Unit;
    private List<LessonDTO> lesson;
}
