package fpt.intern.fa_api.model.mapper;

import fpt.intern.fa_api.model.entity.ClassEntity;
import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.request.AsEntity.UserRequest;
import fpt.intern.fa_api.model.response.AsEntity.ClassResponse;
import fpt.intern.fa_api.model.response.AsEntity.UserResponse;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    //@Mapping(source = "roles.name", target = "roles")
    default UserResponse toResponse(User userEntity){
        UserResponse response = new UserResponse();
        response.setAddress(userEntity.getAddress());
       if(userEntity.getStatus()!=null) {
            response.setStatus(userEntity.getStatus().name());
        }
        else
        {
            response.setStatus(null);
        }
        if(userEntity.getGender()!=null) {
            response.setGender(userEntity.getGender().name());
        }
        else {
            response.setGender(null);
        }
        response.setEmail(userEntity.getEmail());
        response.setId(userEntity.getId());
        response.setDateOfBirth(userEntity.getDateOfBirth());
        response.setPhone(userEntity.getPhone());
        response.setRoles(userEntity.getRoles().getName());
        response.setUsername(userEntity.getUsername());
        return response;
    }
    List<UserResponse> toResponseList(List<User> user);

    @Mapping(source = "roles", target = "roles.name")
    User toEntity(UserRequest userRequest);
}
