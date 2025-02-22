package fpt.intern.fa_api.service.impl;

import fpt.intern.fa_api.exception.NotFoundException;
import fpt.intern.fa_api.model.entity.ClassEntity;
import fpt.intern.fa_api.model.entity.ClassSchedule;
import fpt.intern.fa_api.model.mapper.ClassMapper;
import fpt.intern.fa_api.model.mapper.ClassScheduleMapper;
import fpt.intern.fa_api.model.request.AsEntity.ClassScheduleRequest;
import fpt.intern.fa_api.model.response.AsEntity.ClassScheduleResponse;
import fpt.intern.fa_api.repository.ClassRepository;
import fpt.intern.fa_api.repository.ClassScheduleRepository;
import fpt.intern.fa_api.service.ClassScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassScheduleServiceImpl implements ClassScheduleService {
    private final ClassScheduleRepository repository;
    private final ClassRepository classRepository;
    private final ClassMapper classMapper;
    private final ClassScheduleMapper classScheduleMapper;

    @Override
    public ClassScheduleResponse create(ClassScheduleRequest schedule) {
        ClassSchedule classSchedule = new ClassSchedule();
        classSchedule.setClassEntity(classRepository.findById(schedule.getClassId()).orElseThrow(() -> new RuntimeException("Không tìm thấy class tương ứng")));
        classSchedule.setTimeOfDay(schedule.getTimeOfDay());
        classSchedule.setDayOfWeek(schedule.getDayOfWeek());

        classSchedule = repository.saveAndFlush(classSchedule);

        ClassScheduleResponse classScheduleResponse = classScheduleMapper.toResponse(classSchedule);
        // Entity Class to Response Class
        classScheduleResponse.setClassResponse(classMapper.toResponse(classSchedule.getClassEntity()));

        return classScheduleResponse;
    }

    @Override
    public ClassScheduleResponse update(ClassScheduleRequest schedule) {

        ClassSchedule scheduleChange = repository.findById(schedule.getId()).orElseThrow(()->new RuntimeException("Không tìm thấy thời khóa biểu này"));
        scheduleChange.setDayOfWeek(schedule.getDayOfWeek());
        scheduleChange.setTimeOfDay(schedule.getTimeOfDay());
        repository.save(scheduleChange);

        ClassScheduleResponse classScheduleResponse = classScheduleMapper.toResponse(scheduleChange);
        classScheduleResponse.setClassResponse(classMapper.toResponse(scheduleChange.getClassEntity()));

        return classScheduleResponse;
    }

    @Override
    public List<ClassScheduleResponse> updateAll(List<ClassScheduleRequest> requestList){
        // Request to Entity
        List<ClassSchedule> listNew = new ArrayList<>();

        Long classId = 0L;
        for (ClassScheduleRequest request : requestList) {
            classId = request.getClassId();
            break;
        }

        if (classId == 0L){ return null;}

        List<ClassSchedule> oldList = repository.findByClassID(classId);

        oldList.forEach(old -> {
            delete(old.getId());
        });

        // Entity to response
        List<ClassScheduleResponse> responseList = new ArrayList<>();
        for (ClassScheduleRequest newSchedule : requestList){
            responseList.add(create(newSchedule));
        }
        return responseList;
    }


    @Override
    public void delete(Long id) {
        ClassSchedule schedule = repository.findById(id).orElseThrow(()->new RuntimeException("Không tìm thấy thời khóa biểu nào của lớp này"));
        repository.delete(schedule);
    }

    @Override
    public List<ClassScheduleResponse> getSchedule(ClassScheduleRequest request) {
        List<ClassEntity> classEntities = classRepository.findAllByStart_dateAndeAndEnd_date(request.getStart_day_schedule(), request.getEnd_day_schedule());
        if (classEntities.isEmpty()){
            return null;

        }
        List<ClassScheduleResponse> responseList = new ArrayList<>();

        for(ClassEntity classEntity:classEntities){
            List<ClassSchedule> classScheduleList = repository.findByClassID(classEntity.getId());
            if (classScheduleList.isEmpty()) continue;

            // Entity to response
            classScheduleList.forEach( schedule -> {
                ClassScheduleResponse response = classScheduleMapper.toResponse(schedule);
                response.setClassResponse(classMapper.toResponse(schedule.getClassEntity()));
                responseList.add(response);
            });
        }

        return responseList;
    }

    @Override
    public List<ClassScheduleResponse> getScheduleByClassID(Long id) {
        List<ClassSchedule> classScheduleList = repository.findByClassID(id);
        if (classScheduleList.isEmpty()){
            throw new RuntimeException("Không tìm thấy danh sách thời khóa biểu nào của lớp này");
        }

        List<ClassScheduleResponse> responseList = new ArrayList<>();
        classScheduleList.forEach( schedule -> {
            ClassScheduleResponse response = classScheduleMapper.toResponse(schedule);
            response.setClassResponse(classMapper.toResponse(schedule.getClassEntity()));
            responseList.add(response);
        });

        return responseList;
    }


}
