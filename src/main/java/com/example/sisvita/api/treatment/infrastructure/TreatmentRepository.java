package com.example.sisvita.api.treatment.infrastructure;

import com.example.sisvita.api.treatment.domain.Treatment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TreatmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public TreatmentRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class TreatmentRowMapper implements RowMapper<Treatment> {
        @Override
        public Treatment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Treatment treatment = new Treatment();
            treatment.setId(rs.getInt("id"));
            treatment.setName(rs.getString("name"));
            treatment.setFundament(rs.getString("fundament"));
            return treatment;
        }
    }

    public Boolean save(Treatment treatment) {
        String sql = "INSERT INTO treatment (name, fundament) VALUES (?, ?)";
        return jdbcTemplate.update(sql, treatment.getName(), treatment.getFundament()) > 0;
    }

    public Boolean saveAll(List<Treatment> treatments) {
        String sql = "INSERT INTO treatment (name, fundament) VALUES (?, ?)";
        return jdbcTemplate.batchUpdate(sql, treatments, treatments.size(), (ps, treatment) -> {
            ps.setString(1, treatment.getName());
            ps.setString(2, treatment.getFundament());
        }).length > 0;
    }

    public List<Treatment> findAll() {
        String sql = "SELECT * FROM treatment";
        return jdbcTemplate.query(sql, new TreatmentRowMapper());
    }

    public Treatment findById(Integer id) {
        String sql = "SELECT * FROM treatment WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new TreatmentRowMapper(), id);
    }

}
