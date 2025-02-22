package fpt.intern.fa_api.service;

import fpt.intern.fa_api.model.request.AsEntity.AuthenticationRequest;
import fpt.intern.fa_api.model.request.AsEntity.RegisterRequest;
import fpt.intern.fa_api.model.request.AsEntity.ResetPasswordRequest;
import fpt.intern.fa_api.model.response.ApiResponse;
import fpt.intern.fa_api.model.response.AsEntity.AuthenticationResponse;
import org.springframework.validation.BindingResult;

public interface AuthenticationService {
    public ApiResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);
    ApiResponse resetPassword(ResetPasswordRequest request);
}
