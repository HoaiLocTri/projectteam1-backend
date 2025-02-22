package fpt.intern.fa_api.controller;

import fpt.intern.fa_api.exception.ApplicationException;
import fpt.intern.fa_api.model.entity.Roles;
import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.mapper.RolesMapper;
import fpt.intern.fa_api.model.mapper.UserMapper;
import fpt.intern.fa_api.model.request.AsEntity.AddUserRequest;
import fpt.intern.fa_api.model.response.ApiResponse;
import fpt.intern.fa_api.model.response.AsEntity.PermissionResponse;
import fpt.intern.fa_api.model.response.AsEntity.PermissionResponseList;
import fpt.intern.fa_api.model.response.AsEntity.RolesResponse;
import fpt.intern.fa_api.model.response.AsEntity.UserResponse;
import fpt.intern.fa_api.model.response.StatusEnum;
import fpt.intern.fa_api.service.PermissionService;
import fpt.intern.fa_api.service.RoleService;
import fpt.intern.fa_api.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "User", description = "User management APIs")
@RestController
@RequestMapping("/user/permission")
@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')" )
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @Autowired
    UserMapper mapper;

    @Autowired
    RoleService roleService;
    @Autowired
    RolesMapper rolesMapper;



    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUser() {
        try {

            List<Object> ListclassResponse = permissionService.getAllPermissionByRoles();
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(ListclassResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }
    @PostMapping("/update")
    public ResponseEntity<ApiResponse<PermissionResponse>> update(@RequestBody Roles roles) {
        try {

            Roles ListclassResponse = permissionService.update(roles);
            ApiResponse apiResponse = new ApiResponse();
            PermissionResponse response=rolesMapper.toResponse(ListclassResponse);
            apiResponse.ok(response);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }


}
