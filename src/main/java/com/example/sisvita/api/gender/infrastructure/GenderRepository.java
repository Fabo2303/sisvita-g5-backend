package com.example.sisvita.api.gender.infrastructure;

import com.example.sisvita.api.gender.domain.Gender;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GenderRepository {

    private final JdbcTemplate jdbcTemplate;

    public GenderRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class GenderRowMapper implements RowMapper<Gender> {
        @Override
        public Gender mapRow(ResultSet rs, int rowNum) throws SQLException {
            Gender gender = new Gender();
            gender.setId(rs.getInt("id"));
            gender.setGender(rs.getString("gender"));
            return gender;
        }
    }

    public Boolean save(Gender gender){
        String sql = "INSERT INTO gender (gender) VALUES (?)";
        return jdbcTemplate.update(sql, gender.getGender()) > 0;
    }

    public List<Gender> findAll() {
        String sql = "SELECT * FROM gender";
        return jdbcTemplate.query(sql, new GenderRowMapper());
    }

    public Gender findById(Integer id) {
        String sql = "SELECT * FROM gender WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new GenderRowMapper(), id);
    }
}
