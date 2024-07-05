package com.example.sisvita.api.answer.infrastructure;

import com.example.sisvita.api.answer.domain.Answer;
import com.example.sisvita.api.answer.dto.response.AnswerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JpaAnswerRepository extends JpaRepository<Answer, Integer>{
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO answer (id_question, id_alternative, id_resolved_test) VALUES (:idQuestion, :idAlternative, :idResolvedTest)")
    void insert(@Param("idQuestion") Integer idQuestion, @Param("idAlternative") Integer idAlternative, @Param("idResolvedTest") Integer idResolvedTest);

    @Query(nativeQuery = true, value = "SELECT new com.example.sisvita.api.answer.dto.AnswerResponse(a.id, a.resolvedTest.id, a.question.id, a.alternative.id) FROM Answer a")
    List<AnswerResponse> findAllAnswerResponse();
}
