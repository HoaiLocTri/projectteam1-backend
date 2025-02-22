package fpt.intern.fa_api.repository;

import fpt.intern.fa_api.model.entity.Permission;
import fpt.intern.fa_api.model.response.AsEntity.PermissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
    @Query(value="select roles.name,roles.syllabus,training,class_roles,user from roles",nativeQuery = true)
    List<Object> getPermissionByRoles();
}
