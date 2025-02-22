package fpt.intern.fa_api.model.mapper;

import fpt.intern.fa_api.model.entity.Training;
import fpt.intern.fa_api.model.request.AsEntity.TrainingRequest;
import fpt.intern.fa_api.model.response.AsEntity.TrainingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainingMapper {
    TrainingMapper INSTANCE = Mappers.getMapper(TrainingMapper.class);

//    @Mapping(source = "createdBy.id", target = "createdBy")
//    @Mapping(source = "modifiedBy.id", target = "modifiedBy")
    @Mapping(source = "training.createdBy.username", target = "createdBy")
    @Mapping(source = "training.modifiedBy.username", target = "modifiedBy")
    TrainingResponse toResponse(Training training);

    List<TrainingResponse> toResponseList(List<Training> TrainingList);

    Training toEnity(TrainingRequest request);

    default void setCreate(Training training) {
        training.setCreate();
    }
    default void setModified(Training training) {
        training.setModified();
    }

}
