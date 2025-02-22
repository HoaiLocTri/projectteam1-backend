package fpt.intern.fa_api.model.mapper;

import fpt.intern.fa_api.model.entity.Roles;
import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.request.AsEntity.UserRequest;
import fpt.intern.fa_api.model.response.AsEntity.PermissionResponse;
import fpt.intern.fa_api.model.response.AsEntity.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RolesMapper {
    RolesMapper INSTANCE = Mappers.getMapper( RolesMapper.class );

    //@Mapping(source = "roles.name", target = "roles")
    default PermissionResponse toResponse(Roles roles){
        PermissionResponse response = new PermissionResponse();
        response.setName(roles.getName());
        response.setSyllabus(roles.getSyllabus());
        response.setClass_roles(roles.getClassRoles());
        response.setUser(roles.getUser());
        response.setTraining(roles.getTraining());

        return response;
    }

}
