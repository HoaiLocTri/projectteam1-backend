package fpt.intern.fa_api.model.mapper;

import fpt.intern.fa_api.model.entity.ClassEntity;
import fpt.intern.fa_api.model.entity.Training;
import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.entity.UserClass;
import fpt.intern.fa_api.model.filter.ClassEntityFilter;
import fpt.intern.fa_api.model.request.AsEntity.ClassRequest;
import fpt.intern.fa_api.model.request.ClassSearchCriteria;
import fpt.intern.fa_api.model.response.AsEntity.ClassResponse;
import fpt.intern.fa_api.model.response.AsEntity.TrainingResponse;
import fpt.intern.fa_api.model.response.ClassEntityPageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassMapper {
    ClassMapper INSTANCE = Mappers.getMapper( ClassMapper.class );
    TrainingMapper setTraining = Mappers.getMapper( TrainingMapper.class );
    // Map Entity to Response
//    @Mapping(source = "classEntity.created_by.username", target = "created_by")
//    @Mapping(source = "classEntity.modified_by.username", target = "modified_by")
//    @Mapping(source = "classEntity.userClassList", target = "trainer", qualifiedByName = "toTrainer")
//    @Mapping(source = "classEntity.userClassList", target = "admin", qualifiedByName = "toAdmin")
    default ClassResponse toResponse(ClassEntity classEntity) {
        ClassResponse classResponse = new ClassResponse();

        classResponse.setCreated_by( classEntity.getCreated_by() == null ? null : classEntity.getCreated_by().getId() );
        classResponse.setModified_by( classEntity.getModified_by() == null ? null : classEntity.getModified_by().getId() );
        classResponse.setTrainer( ClassMapper.toTrainer( classEntity.getUserClassList() == null ? new ArrayList<UserClass>() : classEntity.getUserClassList() ) );
        classResponse.setAdmin( ClassMapper.toAdmin( classEntity.getUserClassList() == null ? new ArrayList<UserClass>() : classEntity.getUserClassList() ) );
        classResponse.setId( classEntity.getId() );
        classResponse.setName( classEntity.getName() );
        classResponse.setCode( classEntity.getCode() );
        if ( classEntity.getStatus() != null ) {
            classResponse.setStatus( classEntity.getStatus().name() );
        }
        classResponse.setLocation( classEntity.getLocation() );
        classResponse.setFsu( classEntity.getFsu() );
        classResponse.setStart_date( classEntity.getStart_date() );
        classResponse.setEnd_date( classEntity.getEnd_date() );
        classResponse.setCreated_date( classEntity.getCreated_date() );
        classResponse.setModified_date( classEntity.getModified_date() );

        classResponse.setTraining(setTraining.toResponse(classEntity.getTraining()));

        return classResponse;

    }

    @Named("toTrainer")
    static List<String> toTrainer(List<UserClass> userClassList){
        List<String> response = new ArrayList<>();
        userClassList.forEach(user -> {
            if(user.getUser().getRoles().getName().equals("ROLE_TRAINER"))
                response.add(user.getUser().getUsername()) ;
        });

        return response;
    }

    @Named("toAdmin")
    static List<String> toAdmin(List<UserClass> userClassList){
        List<String> response = new ArrayList<>();
        userClassList.forEach(user -> {
            if(user.getUser().getRoles().getName().equals("ROLE_ADMIN"))
                response.add(user.getUser().getUsername()) ;
        });

        return response;
    }

    List<ClassResponse> toResponseList(List<ClassEntity> classEntityList);

    ClassEntity toEntity(ClassRequest request);

    ClassEntityFilter toFilter(ClassSearchCriteria criteria);

    default ClassEntityPageResponse<ClassResponse> toClassEntityPageResponse(Page<ClassEntity> page) {
        ClassEntityPageResponse<ClassResponse> pageResponse = new ClassEntityPageResponse<>();
        pageResponse.setContent(toResponseList(page.getContent()));
        pageResponse.setPage(page.getNumber());
        pageResponse.setSize(page.getSize());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setTotalSize(page.getTotalElements());
        return pageResponse;
    }
}
