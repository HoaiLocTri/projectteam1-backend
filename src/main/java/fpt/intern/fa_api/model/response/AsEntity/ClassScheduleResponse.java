package fpt.intern.fa_api.model.response.AsEntity;

import fpt.intern.fa_api.model.enums.DayOfWeek;
import fpt.intern.fa_api.model.enums.TimeOfDay;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassScheduleResponse {
    private Long id;
    private ClassResponse classResponse;
    private TimeOfDay timeOfDay;
    private DayOfWeek dayOfWeek;
}
