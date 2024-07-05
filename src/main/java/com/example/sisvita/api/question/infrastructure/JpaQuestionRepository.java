package com.example.sisvita.api.question.infrastructure;

import com.example.sisvita.api.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaQuestionRepository extends JpaRepository<Question, Integer> {
}
