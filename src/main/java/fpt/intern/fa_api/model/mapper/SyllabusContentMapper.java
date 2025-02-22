package fpt.intern.fa_api.model.mapper;

import fpt.intern.fa_api.model.entity.SyllabusContent;
import fpt.intern.fa_api.model.request.AsEntity.SyllabusContentRequest;
import fpt.intern.fa_api.model.response.SyllabusContentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SyllabusContentMapper {
    @Mapping(source = "syllabusId", target = "syllabus.id")
    SyllabusContent toEntity(SyllabusContentRequest request);

    @Mapping(source = "syllabus.id", target = "syllabusId")
    SyllabusContentResponse toResponse(SyllabusContent entity);
}
