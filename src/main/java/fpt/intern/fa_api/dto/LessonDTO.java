package fpt.intern.fa_api.dto;

import fpt.intern.fa_api.model.enums.DeliveryType;
import fpt.intern.fa_api.model.enums.Method;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {
    private String lessonName;
    private String trainingMaterial;
    private Long minute;
    private DeliveryType deliveryType;
    private Method method;
}
