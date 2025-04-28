package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.dto.request.LoginRequest;
import com.mtm.Movie.Theatre.Management.API.dto.response.ApiResponse;
import com.mtm.Movie.Theatre.Management.API.dto.response.JwtResponse;
import com.mtm.Movie.Theatre.Management.API.dto.request.SignupRequest;
import com.mtm.Movie.Theatre.Management.API.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService userService;

    @PostMapping("/signup")
    public ApiResponse<String> signUp(@Valid @RequestBody SignupRequest signupRequest){
         return userService.signUp(signupRequest);
     }

     @PostMapping("/login")
    public ApiResponse<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest){
         return userService.login(loginRequest);
     }

}
