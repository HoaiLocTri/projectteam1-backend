package fpt.intern.fa_api.model.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fpt.intern.fa_api.model.enums.PermissionName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="Permission")
public class Permission {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permission_id")
	private Long id;

	@Column( name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Size(min = 1, max = 1)
	@Column(name = "status")
	private String status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@OneToMany(mappedBy = "permission")
	private List<PermissionRoles> permissionRolesList;
}
