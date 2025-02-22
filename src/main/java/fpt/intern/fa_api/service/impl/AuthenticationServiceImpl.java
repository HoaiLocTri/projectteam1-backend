package fpt.intern.fa_api.service.impl;

import fpt.intern.fa_api.exception.UserExistException;
import fpt.intern.fa_api.model.entity.Roles;
import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.request.AsEntity.AuthenticationRequest;
import fpt.intern.fa_api.model.request.AsEntity.RegisterRequest;
import fpt.intern.fa_api.model.request.AsEntity.ResetPasswordRequest;
import fpt.intern.fa_api.model.response.ApiResponse;
import fpt.intern.fa_api.model.response.AsEntity.AuthenticationResponse;
import fpt.intern.fa_api.model.response.StatusEnum;
import fpt.intern.fa_api.repository.RolesRepository;
import fpt.intern.fa_api.repository.UserRepository;
import fpt.intern.fa_api.security.JwtService;
import fpt.intern.fa_api.service.AuthenticationService;
import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RolesRepository rolesRepository;

    public ApiResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(rolesRepository.findByName("SUPER_ADMIN").orElseThrow(()-> new RuntimeException("Không có role tương ứng")))
                .build();
        if(repository.findByUsername(request.getUsername().trim()).isPresent()) throw new UserExistException("Username đã tồn tại");
        if(repository.findByEmail(request.getEmail().trim()).isPresent()) throw new UserExistException("Email đã tồn tại");

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
        ApiResponse response = new ApiResponse();
        Map token = new HashMap<>();
        token.put("token", jwtToken);
        response.setPayload(token);
        response.setStatus(StatusEnum.SUCCESS);
        return response;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user= repository.findByEmailOrUsername(request.getEmail())
                .orElseThrow();
        Roles rolesuser=user.getRoles();
        var jwtToken = jwtService.generateToken(user,rolesuser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
    @Override
    public ApiResponse resetPassword(ResetPasswordRequest request) {
        User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng"));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(user);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(StatusEnum.SUCCESS);
        apiResponse.setPayload("Thay đổi mật khẩu thành công");
        return apiResponse;
    }

}
