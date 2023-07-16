package com.akshay.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 2, message = "Username must be of minimum 2 characters")
    private String name;
    @Email(message = "Email address is not valid!!")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = "password must be of between 3-10 characters")
    private String password;
    @NotEmpty
    private String about;
    private Set<RoleDto> roles = new HashSet<>();
}
