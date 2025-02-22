package fpt.intern.fa_api.service;

import fpt.intern.fa_api.model.request.AsEntity.SyllabusRequest;
import fpt.intern.fa_api.model.response.AsEntity.SyllabusResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

public interface SyllabusService {
    SyllabusResponse save(SyllabusRequest syllabusRequest, BindingResult bindingResult);

    void saveDataFromCSV(MultipartFile file,String type);
}
