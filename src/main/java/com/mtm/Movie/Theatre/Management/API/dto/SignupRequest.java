package com.mtm.Movie.Theatre.Management.API.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String firstName;
    private  String lastName;
    private String userName;
    private String email;
    private String password;
    private String role;

}
