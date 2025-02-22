package fpt.intern.fa_api.model.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fpt.intern.fa_api.model.enums.PermissionName;
import fpt.intern.fa_api.model.enums.RoleStatus;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="Roles")
@Transactional
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name may not be blank")
    @Column(nullable = false)
    private String name;

    private String description;

    private String status;
    private String classRoles;
    private String Training;
    private String Syllabus;
    private String User;


    private Date createdDate;

    private Date modifiedDate;

    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> userList;

    @OneToMany(mappedBy = "roles")
    private List<PermissionRoles> permissionRolesList;
}
