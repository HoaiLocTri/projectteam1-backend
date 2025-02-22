package fpt.intern.fa_api.model.response.AsEntity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionResponse {
    private String name;
    private String syllabus;
    private String training;
    private String class_roles;
    private String user;

}
