package com.example.sisvita.api.question.infrastructure;

import com.example.sisvita.api.question.domain.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QuestionRepository {

    private final JdbcTemplate jdbcTemplate;

    public QuestionRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class QuestionRowMapper implements RowMapper<Question> {
        @Override
        public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
            Question question = new Question();
            question.setId(rs.getInt("id"));
            question.setImage(rs.getString("image"));
            question.setInverted(rs.getBoolean("inverted"));
            question.setStatement(rs.getString("statement"));
            question.setIdTemplateTest(rs.getInt("id_template_test"));
            return question;
        }
    }

    public Boolean save(Question question) {
        String sql = "INSERT INTO question (image, inverted, statement, id_template_test) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, question.getImage(), question.isInverted(), question.getStatement(), question.getIdTemplateTest()) > 0;
    }

    public Question findById(Integer id) {
        String sql = "SELECT * FROM question WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new QuestionRowMapper(), id);
    }

    public List<Question> findAll() {
        String sql = "SELECT * FROM question";
        return jdbcTemplate.query(sql, new QuestionRowMapper());
    }

}
