package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.request.LoginRequest;
import com.mtm.Movie.Theatre.Management.API.dto.request.SignupRequest;
import com.mtm.Movie.Theatre.Management.API.dto.response.ApiResponse;
import com.mtm.Movie.Theatre.Management.API.dto.response.JwtResponse;
import com.mtm.Movie.Theatre.Management.API.enums.Role;
import com.mtm.Movie.Theatre.Management.API.enums.UserType;
import com.mtm.Movie.Theatre.Management.API.exception.BadRequestException;
import com.mtm.Movie.Theatre.Management.API.exception.UserNotFoundException;
import com.mtm.Movie.Theatre.Management.API.model.User;
import com.mtm.Movie.Theatre.Management.API.repository.UserRepository;
import com.mtm.Movie.Theatre.Management.API.service.AuthService;
import com.mtm.Movie.Theatre.Management.API.utility.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements  AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Override
    public ApiResponse<JwtResponse> login(@Valid LoginRequest loginRequest) {
        var user = userRepository.findByUserNameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail())
                .orElseThrow(()-> new UserNotFoundException("User Not Found with username"));
        if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            JwtResponse token = new JwtResponse();
            token.setEmail(user.getEmail());
            token.setToken(jwtUtils.generateJwtToken(user));
            token.setUsername(user.getUsername());
            token.setRoles(List.of(user.getRole().name()));
            return ApiResponse.ok("login successful", token);
        }
        throw new BadRequestException("password does not match");
    }

    @Override
    public ApiResponse<String> signUp(SignupRequest signupRequest) {
        var user = new User();
        user.setUserName(signupRequest.getUserName());
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        if(signupRequest.getUserType().equals(UserType.ADMIN)){
            user.setUserType(UserType.ADMIN);
            user.setRole(Role.ADMIN);
        } else {
            user.setUserType(UserType.USER);
            user.setRole(Role.USER);
        }

        if (userRepository.findByUserName(signupRequest.getUserName()).isPresent()) {
            throw new BadRequestException("Username is already taken");
        }

        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new BadRequestException("Email is already in use");
        }

        userRepository.save(user);
        return ApiResponse.ok("signup successful");
    }
}
