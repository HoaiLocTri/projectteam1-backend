package fpt.intern.fa_api.service;

import fpt.intern.fa_api.model.entity.ClassSchedule;
import fpt.intern.fa_api.model.request.AsEntity.ClassScheduleRequest;
import fpt.intern.fa_api.model.response.AsEntity.ClassScheduleResponse;

import java.time.LocalDate;
import java.util.List;

public interface ClassScheduleService {
    ClassScheduleResponse create(ClassScheduleRequest request);
    ClassScheduleResponse update(ClassScheduleRequest request);
    List<ClassScheduleResponse> updateAll(List<ClassScheduleRequest> requestList);
    void delete(Long id);
    List<ClassScheduleResponse> getSchedule(ClassScheduleRequest request);
    List<ClassScheduleResponse> getScheduleByClassID(Long id);
}
