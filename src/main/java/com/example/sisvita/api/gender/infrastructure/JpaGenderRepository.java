package com.example.sisvita.api.gender.infrastructure;

import com.example.sisvita.api.gender.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaGenderRepository extends JpaRepository<Gender, Integer> {
}
