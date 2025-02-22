package fpt.intern.fa_api.model.response.AsEntity;

import fpt.intern.fa_api.model.entity.ClassEntity;
import fpt.intern.fa_api.model.entity.Roles;
import fpt.intern.fa_api.model.enums.Gender;
import fpt.intern.fa_api.model.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String gender;
    private String phone;
    private Date dateOfBirth;
    private String address;
    private String status;
    private String roles;
    private List<ClassResponse> classEnityList;
}
