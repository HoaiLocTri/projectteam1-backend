package fpt.intern.fa_api.controller;

import fpt.intern.fa_api.model.response.ApiResponse;
import fpt.intern.fa_api.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("fa/api/roles")
@RequiredArgsConstructor
public class RoleController {
    final private RoleService roleService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllRole() {
        List<String> result = new ArrayList<>();
        roleService.getAllRole().forEach(roles -> {
            result.add(roles.getName());
        });
        ApiResponse response = new ApiResponse();
        response.setPayload(result);
        return ResponseEntity.ok(response);
    }
}
