package fpt.intern.fa_api.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class ValidationHandle {
    public static Map<String,String> showAllError(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        result.getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }
}
