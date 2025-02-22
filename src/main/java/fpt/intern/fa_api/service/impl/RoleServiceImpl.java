package fpt.intern.fa_api.service.impl;

import fpt.intern.fa_api.model.entity.Roles;
import fpt.intern.fa_api.repository.RolesRepository;
import fpt.intern.fa_api.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    final private RolesRepository rolesRepository;
    @Override
    public List<Roles> getAllRole() {
        return rolesRepository.findAll();
    }
}
