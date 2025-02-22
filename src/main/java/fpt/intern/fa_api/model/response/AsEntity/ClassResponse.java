package fpt.intern.fa_api.model.response.AsEntity;

import fpt.intern.fa_api.model.entity.Training;
import fpt.intern.fa_api.model.enums.ClassStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import fpt.intern.fa_api.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Setter
@Getter
public class ClassResponse implements Serializable {

	private Long id;

	private String name;

	private String code;

	private String status;

	private String location;

	private String fsu;

	private Date start_date;

	private Date end_date;

	private Long created_by;

	private Date created_date;

	private Long modified_by;

	private Date modified_date;

	private TrainingResponse training;

	private List<String> trainer;

	private List<String> admin;
}
