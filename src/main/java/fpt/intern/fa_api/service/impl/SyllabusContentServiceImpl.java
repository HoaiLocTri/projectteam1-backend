package fpt.intern.fa_api.service.impl;

import fpt.intern.fa_api.dto.LessonDTO;
import fpt.intern.fa_api.dto.UnitDTO;
import fpt.intern.fa_api.exception.NotFoundException;
import fpt.intern.fa_api.model.entity.Syllabus;
import fpt.intern.fa_api.model.entity.SyllabusContent;
import fpt.intern.fa_api.model.mapper.SyllabusContentMapper;
import fpt.intern.fa_api.model.request.AsEntity.SyllabusContentRequest;
import fpt.intern.fa_api.model.response.SyllabusContentResponse;
import fpt.intern.fa_api.repository.SyllabusContentRepository;
import fpt.intern.fa_api.service.SyllabusContentService;
import fpt.intern.fa_api.util.SyllabusContentTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SyllabusContentServiceImpl implements SyllabusContentService {
    private final SyllabusContentRepository repository;

    @Override
    public void createOutline(List<SyllabusContentRequest> requests) {
        List<SyllabusContent> listEntity = new ArrayList<>();
        for (SyllabusContentRequest syllabusContentRequest : requests) {
            for (LessonDTO lessonDTO : syllabusContentRequest.getLesson()) {
                SyllabusContent temp = new SyllabusContent();
                temp.setDay(syllabusContentRequest.getDay());
                temp.setSyllabus(Syllabus.builder().id(syllabusContentRequest.getSyllabusId()).build());
                temp.setUnit(syllabusContentRequest.getUnitNumber());
                temp.setUnitName(syllabusContentRequest.getUnitName());
                temp.setLessonName(lessonDTO.getLessonName());
                temp.setMethod(lessonDTO.getMethod());
                temp.setMinute(lessonDTO.getMinute());
                temp.setDeliveryType(lessonDTO.getDeliveryType());
                temp.setTrainingMaterial(lessonDTO.getTrainingMaterial());
                repository.save(temp);
            }
        }
    }

    @Override
    public List<SyllabusContentResponse> getSyllabusContentBySyllabusId(Long id) {
        List<SyllabusContent> syllabusContentList = repository.findBySyllabus_Id(id);
        if (syllabusContentList.isEmpty())
            throw new NotFoundException("Không tồn tại syllabus id này");
        List<SyllabusContentResponse> transformedList = SyllabusContentTransformer.transform(syllabusContentList);
        return transformedList;
}
}
