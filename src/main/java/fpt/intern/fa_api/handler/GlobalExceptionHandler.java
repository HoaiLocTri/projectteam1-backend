package fpt.intern.fa_api.handler;

import fpt.intern.fa_api.exception.NotFoundException;
import fpt.intern.fa_api.exception.UserExistException;
import fpt.intern.fa_api.model.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException ex) {
        ApiResponse apiResponse = new ApiResponse();
        Map<String, String> error = new HashMap<>();
        error.put("Lỗi: ", ex.getMessage());
        apiResponse.error(error);
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(apiResponse);
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ApiResponse> handleUserExistException(UserExistException ex) {
        ApiResponse apiResponse = new ApiResponse();
        Map<String, String> error = new HashMap<>();
        error.put("Lỗi: ", ex.getMessage());
        apiResponse.error(error);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handlerSyllabusContentExistException(SQLIntegrityConstraintViolationException ex) {
        ApiResponse apiResponse = new ApiResponse();
        Map<String, String> error = new HashMap<>();
        error.put("Lỗi: ", ex.getMessage());
        apiResponse.error(error);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }
}
