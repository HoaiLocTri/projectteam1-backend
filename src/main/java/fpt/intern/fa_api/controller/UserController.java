package fpt.intern.fa_api.controller;

import fpt.intern.fa_api.exception.ApplicationException;
import fpt.intern.fa_api.model.entity.Roles;
import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.mapper.UserMapper;
import fpt.intern.fa_api.model.request.AsEntity.AddUserRequest;
import fpt.intern.fa_api.model.response.ApiResponse;
import fpt.intern.fa_api.model.response.AsEntity.UserResponse;
import fpt.intern.fa_api.model.response.StatusEnum;
import fpt.intern.fa_api.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "User management APIs")
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')" )
public class UserController {
    @Autowired
    UserService repository;

    @Autowired
    UserMapper mapper;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> addUser(@RequestBody AddUserRequest request) throws MessagingException {
        User user = mapper.toEntity(request);
        User result = repository.insert(user);
        ApiResponse response = new ApiResponse();
        response.setPayload(mapper.toResponse(result));
        response.setStatus(StatusEnum.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/update-user")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody AddUserRequest request) throws MessagingException {
        User user = mapper.toEntity(request);
        User result = repository.update(user);
        ApiResponse response = new ApiResponse();
        response.setPayload(mapper.toResponse(result));
        response.setStatus(StatusEnum.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUser() {
        try {

            //Nếu không có limit thì mặc định limit = 5
            List<UserResponse> ListclassResponse = repository.findAll();
            System.out.print(ListclassResponse);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(ListclassResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> findUserById(@PathVariable Long id) {
        try {

            //Nếu không có limit thì mặc định limit = 5
           UserResponse ListclassResponse = repository.FindById(id);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(ListclassResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }


}
