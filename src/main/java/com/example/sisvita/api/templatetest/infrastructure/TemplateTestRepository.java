package com.example.sisvita.api.templatetest.infrastructure;

import com.example.sisvita.api.templatetest.domain.TemplateTest;
import com.example.sisvita.api.templatetest.dto.TemplateTestResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TemplateTestRepository {

    private final JdbcTemplate jdbcTemplate;

    public TemplateTestRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class TemplateTestRowMapper implements RowMapper<TemplateTest> {
        @Override
        public TemplateTest mapRow(ResultSet rs, int rowNum) throws SQLException {
            TemplateTest templateTest = new TemplateTest();
            templateTest.setId(rs.getInt("id"));
            templateTest.setName(rs.getString("name"));
            templateTest.setDescription(rs.getString("description"));
            templateTest.setAuthor(rs.getString("author"));
            return templateTest;
        }
    }

    private static final class TemplateTestResponseRowMapper implements RowMapper<TemplateTestResponse> {
        @Override
        public TemplateTestResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            TemplateTestResponse templateTestResponse = new TemplateTestResponse();
            templateTestResponse.setId(rs.getInt("id"));
            templateTestResponse.setName(rs.getString("name"));
            return templateTestResponse;
        }
    }

    public Boolean save(TemplateTest templateTest) {
        String sql = "INSERT INTO template_test (name, description, author) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, templateTest.getName(), templateTest.getDescription(), templateTest.getAuthor()) > 0;
    }

    public TemplateTest findById(int id) {
        String sql = "SELECT * FROM template_test WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new TemplateTestRowMapper(), id);
    }

    public List<TemplateTest> findAll() {
        String sql = "SELECT * FROM template_test";
        return jdbcTemplate.query(sql, new TemplateTestRowMapper());
    }

    public List<String> findAllName() {
        String sql = "SELECT name FROM template_test";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<String> findClassificationNameByName(String name) {
        String sql = "SELECT c.interpretation FROM classification c JOIN template_test t ON c.id_template_test = t.id WHERE t.name = ?";
        return jdbcTemplate.queryForList(sql, String.class, name);
    }

    public List<TemplateTestResponse> findAllTemplateTestResponse() {
        String sql = "SELECT t.id, t.name FROM template_test t";
        return jdbcTemplate.query(sql, new TemplateTestResponseRowMapper());
    }
}
