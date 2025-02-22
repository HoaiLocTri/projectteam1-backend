package fpt.intern.fa_api.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "group_permission_roles")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRoles {
    @EmbeddedId
    private PermissionRolesId id;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @ManyToOne
    @MapsId("rolesId")
    @JoinColumn(name = "roles_id")
    private Roles roles;
}
