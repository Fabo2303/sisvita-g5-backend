package com.example.sisvita.api.classification.infrastructure;

import com.example.sisvita.api.classification.domain.Classification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClassificationRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClassificationRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class ClassificationRowMapper implements RowMapper<Classification> {
        @Override
        public Classification mapRow(ResultSet rs, int rowNum) throws SQLException {
            Classification classification = new Classification();
            classification.setId(rs.getInt("id"));
            classification.setMinimum(rs.getInt("minimum"));
            classification.setMaximum(rs.getInt("maximum"));
            return classification;
        }
    }

    public Classification findById(int id){
        String sql = "SELECT * FROM classification WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ClassificationRowMapper(), id);
    }

    public List<Classification> findAll(){
        String sql = "SELECT * FROM classification";
        return jdbcTemplate.query(sql, new ClassificationRowMapper());
    }

    public Boolean save(Classification classification){
        String sql = "INSERT INTO classification (minimum, maximum) VALUES (?, ?)";
        return jdbcTemplate.update(sql, classification.getMinimum(), classification.getMaximum()) > 0;
    }

    public Classification findByTemplateTestIdAndResult(Integer idTemplateTest, int result) {
        String sql = "SELECT * FROM classification WHERE id_template_test = ? AND ? BETWEEN minimum AND maximum";
        return jdbcTemplate.queryForObject(sql, new ClassificationRowMapper(), idTemplateTest, result);
    }

    public List<Classification> findByTemplateTestId(Integer idTemplateTest) {
        String sql = "SELECT * FROM classification WHERE id_template_test = ?";
        return jdbcTemplate.query(sql, new ClassificationRowMapper(), idTemplateTest);
    }

    public List<Classification> findByTemplateTestName(String templateTestName) {
        String sql = "SELECT * FROM classification WHERE template_test_name = ?";
        return jdbcTemplate.query(sql, new ClassificationRowMapper(), templateTestName);
    }
}