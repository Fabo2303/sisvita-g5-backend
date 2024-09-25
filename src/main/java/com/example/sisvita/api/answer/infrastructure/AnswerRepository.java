package com.example.sisvita.api.answer.infrastructure;

import com.example.sisvita.api.answer.domain.Answer;
import com.example.sisvita.api.answer.dto.response.AnswerResponseDataPatient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AnswerRepository {

    private final JdbcTemplate jdbcTemplate;

    public AnswerRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class AnswerMapper implements RowMapper<Answer> {
        @Override
        public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Answer answer = new Answer();
            answer.setId(rs.getInt("id"));
            answer.setIdQuestion(rs.getInt("id_question"));
            answer.setIdAlternative(rs.getInt("id_alternative"));
            answer.setIdResolvedTest(rs.getInt("id_resolved_test"));
            return answer;
        }
    }

    private static final class AnswerResponseDataPatientMapper implements RowMapper<AnswerResponseDataPatient> {
        @Override
        public AnswerResponseDataPatient mapRow(ResultSet rs, int rowNum) throws SQLException {
            AnswerResponseDataPatient answerResponseDataPatient = new AnswerResponseDataPatient();
            answerResponseDataPatient.setAlternative(rs.getString("alternative"));
            answerResponseDataPatient.setStatement(rs.getString("statement"));
            return answerResponseDataPatient;
        }
    }

    public Answer findById(int id) {
        String sql = "SELECT * FROM answer WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new AnswerMapper(), id);
    }

    public List<Answer> findAll() {
        String sql = "SELECT * FROM answer";
        return jdbcTemplate.query(sql, new AnswerMapper());
    }

    @Transactional
    public Boolean save(Answer answer) {
        String sql = "INSERT INTO answer (id_question, id_alternative, id_resolved_test) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, answer.getIdQuestion(), answer.getIdAlternative(), answer.getIdResolvedTest()) > 0;
    }

    public List<AnswerResponseDataPatient> findAllAnswerResponseDataPatient(int idResolvedTest) {
        String sql = "SELECT q.statement, al.alternative FROM answer a " +
                "JOIN question q ON a.id_question = q.id " +
                "JOIN alternative al ON a.id_alternative = al.id " +
                "WHERE a.id_resolved_test = ?";
        return jdbcTemplate.query(sql, new AnswerResponseDataPatientMapper(), idResolvedTest);
    }

    /*@Query(nativeQuery = true, value = "SELECT new com.example.sisvita.api.answer.dto.AnswerResponse(a.id, a.resolvedTest.id, a.question.id, a.alternative.id) FROM Answer a")
    List<AnswerResponse> findAllAnswerResponse();*/
}
