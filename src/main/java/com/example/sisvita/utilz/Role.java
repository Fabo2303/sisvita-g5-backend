package com.example.sisvita.utilz;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum Role {
    SPECIALIST(List.of(
            Permission.READ_ALL_TESTS,
            Permission.TAKE_TEST,
            Permission.SEE_YOUR_TESTS,
            Permission.SEE_YOUR_CONSIGNATIONS)
    ),
    PATIENT(List.of(
            Permission.READ_ALL_PATIENTS,
            Permission.READ_ALL_TESTS,
            Permission.SAVE_CONSIGNATION,
            Permission.READ_ALL_CONSIGNATIONS,
            Permission.SEE_HEAT_MAP,
            Permission.SEE_TABLE_REPORT,
            Permission.SEE_YOUR_CONSIGNATIONS)),
    ADMIN(Arrays.asList(
            Permission.READ_ALL_USERS,
            Permission.READ_ALL_TESTS,
            Permission.SAVE_CONSIGNATION,
            Permission.READ_ALL_CONSIGNATIONS,
            Permission.SEE_HEAT_MAP,
            Permission.SEE_TABLE_REPORT,
            Permission.TAKE_TEST,
            Permission.SEE_YOUR_TESTS,
            Permission.SEE_YOUR_CONSIGNATIONS)
    );

    private List<Permission> permissions;

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
