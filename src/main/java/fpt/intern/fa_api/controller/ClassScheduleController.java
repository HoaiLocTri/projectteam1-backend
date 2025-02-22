package fpt.intern.fa_api.controller;

import fpt.intern.fa_api.model.request.AsEntity.ClassScheduleRequest;
import fpt.intern.fa_api.model.response.ApiResponse;
import fpt.intern.fa_api.model.response.AsEntity.ClassScheduleResponse;
import fpt.intern.fa_api.service.ClassScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fa/api")
@RequiredArgsConstructor
public class ClassScheduleController {
    private final ClassScheduleService classScheduleService;

    @PostMapping("/schedule")
    public ResponseEntity<ApiResponse<ClassScheduleResponse>> createSchedule(@RequestBody ClassScheduleRequest request) {
        ClassScheduleResponse response = classScheduleService.create(request);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(response);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/schedule")
    public ResponseEntity<ApiResponse<ClassScheduleResponse>> updateSchedule(@RequestBody ClassScheduleRequest request){
        ClassScheduleResponse response = classScheduleService.update(request);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(response);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/allSchedule")
    public ResponseEntity<ApiResponse<List<ClassScheduleResponse>>> updateAllSchedule(@RequestBody List<ClassScheduleRequest> requestList){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(classScheduleService.updateAll(requestList));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/schedule/list")
    public ResponseEntity<ApiResponse<List<ClassScheduleResponse>>> getSchedule(@RequestBody ClassScheduleRequest request) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(classScheduleService.getSchedule(request));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/schedule/{classID}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')" )
    public ResponseEntity<ApiResponse<List<ClassScheduleResponse>>> getListScheduleByClassID(@PathVariable long classID){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(classScheduleService.getScheduleByClassID(classID));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/schedule/{id}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')" )
    public ResponseEntity<ApiResponse<ClassScheduleResponse>> deleteSchedule(@PathVariable long id){
        classScheduleService.delete(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok();
        return ResponseEntity.ok(apiResponse);
    }
}
