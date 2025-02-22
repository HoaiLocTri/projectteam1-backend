package fpt.intern.fa_api.service;

import fpt.intern.fa_api.model.entity.SyllabusContent;
import fpt.intern.fa_api.model.request.AsEntity.SyllabusContentRequest;
import fpt.intern.fa_api.model.response.SyllabusContentResponse;

import java.util.List;

public interface SyllabusContentService {
    void createOutline(List<SyllabusContentRequest> requests);

    List<SyllabusContentResponse> getSyllabusContentBySyllabusId(Long id);
}
