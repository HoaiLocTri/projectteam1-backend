package fpt.intern.fa_api.service.impl;

import fpt.intern.fa_api.exception.UserExistException;
import fpt.intern.fa_api.model.entity.Roles;
import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.response.AsEntity.PermissionResponse;
import fpt.intern.fa_api.repository.PermissionRepository;
import fpt.intern.fa_api.repository.RolesRepository;
import fpt.intern.fa_api.service.PermissionService;
import fpt.intern.fa_api.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    RolesRepository rolesRepository;

    @Override
    public List<Object> getAllPermissionByRoles() {
        List<Object> list=permissionRepository.getPermissionByRoles();
        System.out.println(list);
        return list;
    }

    @Override
    public Roles update(Roles roles) {

        Optional<Roles> rolesEx = rolesRepository.findById(roles.getId().describeConstable().orElse(0L));
        if(rolesEx.isPresent())
        {
            Roles rolesLoad = rolesEx.get();
            rolesLoad.setTraining(roles.getTraining());
            rolesLoad.setSyllabus(roles.getSyllabus());
            rolesLoad.setClassRoles(roles.getClassRoles());
            rolesLoad.setUser(roles.getUser());
            Roles result = rolesRepository.saveAndFlush(rolesLoad);

            return result;
        }
        else
        {
            throw  new UserExistException("Người dùng không tồn tại");
        }
    }


}
