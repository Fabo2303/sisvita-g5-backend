package com.example.sisvita.api.alternative.infrastructure;

import com.example.sisvita.api.alternative.domain.Alternative;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AlternativeRepository {

    private final JdbcTemplate jdbcTemplate;

    public AlternativeRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class AlternativeMapper implements RowMapper<Alternative>{
        @Override
        public Alternative mapRow(ResultSet rs, int rowNum) throws SQLException {
            Alternative alternative = new Alternative();
            alternative.setId(rs.getInt("id"));
            alternative.setDescription(rs.getString("description"));
            alternative.setScore(rs.getInt("score"));
            alternative.setInvertedScore(rs.getInt("inverted_score"));
            alternative.setIdTemplateTest(rs.getInt("id_template_test"));
            return alternative;
        }
    }

    public Alternative findById(int id){
        String sql = "SELECT * FROM alternative WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new AlternativeMapper(), id);
    }

    public List<Alternative> findAll(){
        String sql = "SELECT * FROM alternative";
        return jdbcTemplate.query(sql, new AlternativeMapper());
    }

    public Boolean save(Alternative alternative){
        String sql = "INSERT INTO alternative (description, score, inverted_score, id_template_test) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, alternative.getDescription(), alternative.getScore(), alternative.getInvertedScore(), alternative.getIdTemplateTest()) > 0;
    }
}
