package com.grupo5.sisvita.api.dto;

import com.grupo5.sisvita.api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String document;

    public static UserDTO fromEntity(User user) {
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getRole().name(),
            user.getPersona().getDocument()
        );
    }
}
