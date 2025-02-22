package fpt.intern.fa_api.controller;

import fpt.intern.fa_api.exception.ApplicationException;
import fpt.intern.fa_api.exception.NotFoundException;
import fpt.intern.fa_api.exception.ValidationException;
import fpt.intern.fa_api.model.entity.Syllabus;
import fpt.intern.fa_api.model.helper.CSVHelper;
import fpt.intern.fa_api.model.mapper.SyllabusMapper;
import fpt.intern.fa_api.model.request.AsEntity.SyllabusContentRequest;
import fpt.intern.fa_api.model.request.AsEntity.SyllabusRequest;
import fpt.intern.fa_api.model.response.ApiResponse;
import fpt.intern.fa_api.model.response.AsEntity.SyllabusResponse;
import fpt.intern.fa_api.model.response.StatusEnum;
import fpt.intern.fa_api.repository.SyllabusRepository;
import fpt.intern.fa_api.service.SyllabusContentService;
import fpt.intern.fa_api.service.SyllabusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fa/api")
public class SyllabusController {
    @Autowired
    SyllabusRepository syllabusRepository;
    @Autowired
    SyllabusService syllabusService;
    @Autowired
    SyllabusMapper mapper;
    @Autowired
    SyllabusContentService syllabusContentService;


    @GetMapping("/syllabuses")
    public ResponseEntity<ApiResponse<List<Syllabus>>> getAll() {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.ok(mapper.toResponseList(syllabusRepository.findAll()));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/syllabus/{id}")
    public ResponseEntity<ApiResponse<List<Syllabus>>> getSyllabus(@PathVariable Long id) {
        try {
            ApiResponse apiResponse = new ApiResponse();
            Syllabus syllabus = syllabusRepository.findById(id).orElse(null);
            if (syllabus == null)
                throw new NotFoundException("Syllabus Not Found");

            apiResponse.ok(mapper.toResponse(syllabus));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        }
    }

    @PostMapping(value = {"/create/general"})
    public ResponseEntity<ApiResponse> createGeneral(@Valid @RequestBody SyllabusRequest syllabusRequest,
                                                     BindingResult bindingResult) {
        try {
            // Save
            SyllabusResponse syllabusResponse = syllabusService.save(syllabusRequest, bindingResult);

            // Response
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(syllabusResponse);
            return ResponseEntity.ok(apiResponse);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(); // Handle other exceptions
        }
    }

    @PostMapping(value = "/create/outline")
    public ResponseEntity<ApiResponse> createOutline(@Valid @RequestBody List<SyllabusContentRequest> requests) {
        syllabusContentService.createOutline(requests);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(StatusEnum.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/syllabus/upload/{type}")
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable String type) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
//            try {
                syllabusService.saveDataFromCSV(file,type);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.ok(message);
                return ResponseEntity.ok(apiResponse);
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                Map<String, String> error = new HashMap<>();
//                error.put("Error: ", message);
//                ApiResponse apiResponse = new ApiResponse();
//                apiResponse.error(error);
//                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(apiResponse);
//            }
        }
        message = "Please upload a csv file!";
        Map<String, String> error = new HashMap<>();
        error.put("Error: ", message);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

}
