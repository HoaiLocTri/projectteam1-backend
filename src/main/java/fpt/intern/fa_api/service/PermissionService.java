package fpt.intern.fa_api.service;

import fpt.intern.fa_api.model.entity.Roles;
import fpt.intern.fa_api.model.response.AsEntity.PermissionResponse;

import java.util.List;

public interface PermissionService {
    List<Object> getAllPermissionByRoles();
    Roles update(Roles roles);
}
