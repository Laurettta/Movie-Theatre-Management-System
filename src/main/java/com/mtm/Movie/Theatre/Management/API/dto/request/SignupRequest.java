package com.mtm.Movie.Theatre.Management.API.dto.request;

import com.mtm.Movie.Theatre.Management.API.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "Kindly enter your first name")
    private String firstName;

    @NotBlank(message = "Kindly enter your last name")
    private  String lastName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String userName;

    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotNull(message = "Kindly enter a user type")
    private UserType userType;


}
