package fpt.intern.fa_api.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;

@Component
public class ValidatorUtil {

    public HashMap<String, String> toErrors(List<FieldError> fieldErrors) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (fieldErrors != null) {
            for (FieldError fieldError : fieldErrors) {
                hashMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }

        return hashMap;
    }

}
