package com.mtm.Movie.Theatre.Management.API.service;

import com.mtm.Movie.Theatre.Management.API.dto.request.LoginRequest;
import com.mtm.Movie.Theatre.Management.API.dto.response.ApiResponse;
import com.mtm.Movie.Theatre.Management.API.dto.response.JwtResponse;
import com.mtm.Movie.Theatre.Management.API.dto.request.SignupRequest;
import jakarta.validation.Valid;

public interface AuthService {
    ApiResponse<JwtResponse> login(@Valid LoginRequest loginRequest);

    ApiResponse<String> signUp(@Valid SignupRequest signupRequest);
}
