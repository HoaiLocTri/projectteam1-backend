package fpt.intern.fa_api.model.request.AsEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import fpt.intern.fa_api.model.entity.Roles;
import fpt.intern.fa_api.model.enums.Gender;
import fpt.intern.fa_api.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest extends UserRequest {
    private String roles;
    private String username;
    private String email;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private Gender gender;
    private UserStatus status;
}
