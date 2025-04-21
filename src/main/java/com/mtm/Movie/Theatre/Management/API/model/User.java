package com.mtm.Movie.Theatre.Management.API.model;

import com.mtm.Movie.Theatre.Management.API.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
@Builder
public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private Role role;


}
