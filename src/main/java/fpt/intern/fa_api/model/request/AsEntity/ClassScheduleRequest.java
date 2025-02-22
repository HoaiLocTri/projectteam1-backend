package fpt.intern.fa_api.model.request.AsEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import fpt.intern.fa_api.model.enums.DayOfWeek;
import fpt.intern.fa_api.model.enums.TimeOfDay;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ClassScheduleRequest {
    private Long id;
    private Long classId;
    private TimeOfDay timeOfDay;
    private DayOfWeek dayOfWeek;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate start_day_schedule;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate end_day_schedule;


}