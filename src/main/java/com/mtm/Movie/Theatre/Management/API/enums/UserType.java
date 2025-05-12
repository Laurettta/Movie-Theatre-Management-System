package com.mtm.Movie.Theatre.Management.API.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.mtm.Movie.Theatre.Management.API.exception.BadRequestException;
import lombok.Getter;

@Getter
public enum UserType {
    ADMIN("admin"),
    USER("user");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static UserType fromValue(String value) {
        for (UserType userType : UserType.values()) {
            if (userType.value.equalsIgnoreCase(value)) {
                return userType;
            }
        }
        throw new BadRequestException("Invalid userType: " + value + ". Allowed values are: admin, user.");
    }


}
