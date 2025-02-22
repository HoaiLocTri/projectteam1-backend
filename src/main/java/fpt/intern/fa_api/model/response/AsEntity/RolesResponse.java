package fpt.intern.fa_api.model.response.AsEntity;

import fpt.intern.fa_api.model.entity.Permission;
import fpt.intern.fa_api.model.enums.PermissionName;
import fpt.intern.fa_api.model.enums.RoleStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RolesResponse {
    private Long id;
    private String name;
    private String description;
    private RoleStatus status;
    private List<UserResponse> userList;
    private List<PermissionResponse> permissions;
    private String classRoles;
    private String Training;
    private String Syllabus;
    private String User;
}
