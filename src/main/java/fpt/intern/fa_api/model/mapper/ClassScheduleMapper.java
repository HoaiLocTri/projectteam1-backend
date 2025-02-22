package fpt.intern.fa_api.model.mapper;

import fpt.intern.fa_api.model.entity.ClassEntity;
import fpt.intern.fa_api.model.entity.ClassSchedule;
import fpt.intern.fa_api.model.request.AsEntity.ClassScheduleRequest;
import fpt.intern.fa_api.model.response.AsEntity.ClassResponse;
import fpt.intern.fa_api.model.response.AsEntity.ClassScheduleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassScheduleMapper {
    default ClassSchedule toEntity(ClassScheduleRequest request) {
        return ClassSchedule.builder()
                .classEntity(ClassEntity.builder().id(request.getClassId()).build())
                .timeOfDay(request.getTimeOfDay())
                .dayOfWeek(request.getDayOfWeek())
                .build();
    }

    default ClassScheduleResponse toResponse (ClassSchedule classSchedule){
        ClassScheduleResponse classScheduleResponse = new ClassScheduleResponse();
        classScheduleResponse.setId(classSchedule.getId());
        classScheduleResponse.setTimeOfDay(classSchedule.getTimeOfDay());
        classScheduleResponse.setDayOfWeek(classSchedule.getDayOfWeek());

        return  classScheduleResponse;
    }
}