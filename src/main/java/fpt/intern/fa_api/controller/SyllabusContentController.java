package fpt.intern.fa_api.controller;

import fpt.intern.fa_api.model.entity.SyllabusContent;
import fpt.intern.fa_api.model.mapper.SyllabusContentMapper;
import fpt.intern.fa_api.model.mapper.SyllabusMapper;
import fpt.intern.fa_api.model.response.ApiResponse;
import fpt.intern.fa_api.model.response.SyllabusContentResponse;
import fpt.intern.fa_api.service.SyllabusContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("fa/api")
@RequiredArgsConstructor
public class SyllabusContentController {
    private final SyllabusContentService service;
    private final SyllabusContentMapper mapper;

    // Trả về và syllabus-content
    @GetMapping("syllabus-content/{id}")
    public ResponseEntity<?> getSyllabusContentById(@PathVariable Long id) {
        List<SyllabusContentResponse> responses =  service.getSyllabusContentBySyllabusId(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(responses);
        return ResponseEntity.ok(apiResponse);
    }
}
