package fpt.intern.fa_api.model.request.AsEntity;

import fpt.intern.fa_api.model.entity.Training;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.enums.ClassStatus;

@Setter
@Getter
@Data
public class ClassRequest implements Serializable {

	private Long id;

	private String name;

	private String code;

	private String status;

	private String location;

	private String fsu;

	private Date start_date;

	private Date end_date;

	private User created_by;

	private Date created_date;

	private User modified_by;

	private Date modified_date;

	private List<User> userList;

	private Training training;
}
