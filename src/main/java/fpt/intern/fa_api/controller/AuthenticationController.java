package fpt.intern.fa_api.controller;

import fpt.intern.fa_api.model.request.AsEntity.AuthenticationRequest;
import fpt.intern.fa_api.model.request.AsEntity.RegisterRequest;
import fpt.intern.fa_api.model.request.AsEntity.ResetPasswordRequest;
import fpt.intern.fa_api.model.response.ApiResponse;
import fpt.intern.fa_api.model.response.AsEntity.AuthenticationResponse;
import fpt.intern.fa_api.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Authentication APIs")
@RestController
@RequestMapping("fa/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody ResetPasswordRequest request) {
        return ResponseEntity.ok(service.resetPassword(request));
    }

}