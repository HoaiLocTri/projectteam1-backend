package fpt.intern.fa_api.controller;

import fpt.intern.fa_api.exception.ApplicationException;
import fpt.intern.fa_api.exception.NotFoundException;
import fpt.intern.fa_api.exception.ValidationException;
import fpt.intern.fa_api.model.request.AsEntity.TrainingRequest;
import fpt.intern.fa_api.model.response.ApiResponse;
import fpt.intern.fa_api.model.response.AsEntity.TrainingResponse;
import fpt.intern.fa_api.service.TrainingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.List;

@Tag(name = "Training Program", description = "Training Program management APIs")
@RestController
@RequestMapping("/fa/api/training")
public class TrainingController {

    @Autowired
    TrainingService trainingRepository;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<TrainingResponse>>> getList() {
        try {
            List<TrainingResponse> listTrainingResponse = trainingRepository.findAll();

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(listTrainingResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @GetMapping("/list/{trainingId}")
    public ResponseEntity<ApiResponse<TrainingResponse>> findTrainingByID(@PathVariable Long trainingId){
        try {
            ApiResponse apiResponse = new ApiResponse();

            TrainingResponse trainingResponse = trainingRepository.findTrainingByID(trainingId);

            apiResponse.ok(trainingResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TrainingResponse>> create(@Valid @RequestBody TrainingRequest trainingRequest){
        try{
            TrainingResponse trainingResponse = trainingRepository.insert(trainingRequest);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(trainingResponse);
            return ResponseEntity.ok(apiResponse);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<TrainingResponse>> update(@Valid @RequestBody TrainingRequest trainingRequest){
        try{
            TrainingResponse trainingResponse = trainingRepository.update(trainingRequest);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(trainingResponse);
            return ResponseEntity.ok(apiResponse);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainingResponse>> delete(@PathVariable Long id){
        try {

            trainingRepository.delte(id);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok();
            return ResponseEntity.ok(apiResponse);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(); // Handle other exceptions
        }
    }


}
