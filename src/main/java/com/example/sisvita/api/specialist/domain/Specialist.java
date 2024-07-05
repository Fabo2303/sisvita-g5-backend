package com.example.sisvita.api.specialist.domain;

import com.example.sisvita.api.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Specialist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String license;
    private String specialty;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
}
