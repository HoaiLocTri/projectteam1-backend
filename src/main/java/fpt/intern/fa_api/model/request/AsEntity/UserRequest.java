package fpt.intern.fa_api.model.request.AsEntity;

import fpt.intern.fa_api.model.entity.Roles;
import fpt.intern.fa_api.model.enums.Gender;
import fpt.intern.fa_api.model.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "")
    private String username;
    private Gender gender; // MALE, FEMALE
    private String phone;
    private Date dateOfBirth;
    private String address;
    private UserStatus status;
    @Email(message = "")
    private String email;
    private String roles;
    private String password;
}
