package com.example.sisvita.api.user.dto;

import com.example.sisvita.api.person.dto.PersonRequest;
import com.example.sisvita.api.user.domain.User;
import com.example.sisvita.utilz.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String password;
    private Role role;
    private PersonRequest personRequest;

    public static User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        return user;
    }
}
