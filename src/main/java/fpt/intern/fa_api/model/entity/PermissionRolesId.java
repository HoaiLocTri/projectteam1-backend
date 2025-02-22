package fpt.intern.fa_api.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PermissionRolesId implements Serializable {
    private Long permissionId;
    private Long rolesId;
}
