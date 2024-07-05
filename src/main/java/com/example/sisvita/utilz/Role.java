package com.example.sisvita.utilz;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum Role {
    SPECIALIST(List.of(Permission.READ_ALL_USERS)),
    PATIENT(List.of(Permission.READ_ALL_USERS)),
    ADMIN(Arrays.asList(Permission.READ_ALL_USERS, Permission.CREATE_USER));

    private List<Permission> permissions;

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
