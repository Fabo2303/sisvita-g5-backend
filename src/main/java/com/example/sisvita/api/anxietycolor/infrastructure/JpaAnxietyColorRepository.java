package com.example.sisvita.api.anxietycolor.infrastructure;

import com.example.sisvita.api.anxietycolor.domain.AnxietyColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAnxietyColorRepository extends JpaRepository<AnxietyColor, Integer>{
}
