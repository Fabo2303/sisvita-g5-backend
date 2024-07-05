package com.example.sisvita.api.alternative.infrastructure;

import com.example.sisvita.api.alternative.domain.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAlternativeRepository extends JpaRepository<Alternative, Integer> {
}
