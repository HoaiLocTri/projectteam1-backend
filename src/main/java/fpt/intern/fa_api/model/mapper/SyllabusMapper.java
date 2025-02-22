package fpt.intern.fa_api.model.mapper;

import fpt.intern.fa_api.model.entity.Syllabus;
import fpt.intern.fa_api.model.request.AsEntity.SyllabusRequest;
import fpt.intern.fa_api.model.response.AsEntity.SyllabusResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface SyllabusMapper {
    Syllabus toEntity(SyllabusRequest syllabusRequest);

    List<Syllabus> toEntityList(List<SyllabusRequest> syllabusRequestList);

    @Mapping(target = "author.id", source = "syllabus.createdBy.id")
    @Mapping(target = "author.username", source = "syllabus.createdBy.username")
    @Mapping(target = "author.email", source = "syllabus.createdBy.email")
    @Mapping(target = "author.gender", source = "syllabus.createdBy.gender")
    @Mapping(target = "author.phone", source = "syllabus.createdBy.phone")
    @Mapping(target = "author.dateOfBirth", source = "syllabus.createdBy.dateOfBirth")
    @Mapping(target = "author.address", source = "syllabus.createdBy.address")
    @Mapping(target = "author.status", source = "syllabus.createdBy.status")
    @Mapping(target = "author.roles", source = "syllabus.createdBy.roles.name")
    @Mapping(target = "author.classEnityList", source = "syllabus.createdBy.userClassList")
    SyllabusResponse toResponse(Syllabus syllabus);

    @Mapping(target = "author.id", source = "syllabus.createdBy.id")
    @Mapping(target = "author.username", source = "syllabus.createdBy.username")
    @Mapping(target = "author.email", source = "syllabus.createdBy.email")
    @Mapping(target = "author.gender", source = "syllabus.createdBy.gender")
    @Mapping(target = "author.phone", source = "syllabus.createdBy.phone")
    @Mapping(target = "author.dateOfBirth", source = "syllabus.createdBy.dateOfBirth")
    @Mapping(target = "author.address", source = "syllabus.createdBy.address")
    @Mapping(target = "author.status", source = "syllabus.createdBy.status")
    @Mapping(target = "author.roles", source = "syllabus.createdBy.roles.name")
    @Mapping(target = "author.classEnityList", source = "syllabus.createdBy.userClassList")
    List<SyllabusResponse> toResponseList(List<Syllabus> syllabusList);



}
