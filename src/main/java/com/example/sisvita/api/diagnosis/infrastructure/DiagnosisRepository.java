package com.example.sisvita.api.diagnosis.infrastructure;

import com.example.sisvita.api.diagnosis.domain.Diagnosis;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DiagnosisRepository {

    private final JdbcTemplate jdbcTemplate;

    public DiagnosisRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class DiagnosisMapper implements RowMapper<Diagnosis> {
        @Override
        public Diagnosis mapRow(ResultSet rs, int rowNum) throws SQLException {
            Diagnosis diagnosis = new Diagnosis();
            diagnosis.setId(rs.getInt("id"));
            diagnosis.setName(rs.getString("name"));
            diagnosis.setFundament(rs.getString("fundament"));
            return diagnosis;
        }
    }

    public Boolean save(Diagnosis diagnosis) {
        String sql = "INSERT INTO diagnosis (name, fundament) VALUES (?, ?)";
        return jdbcTemplate.update(sql, diagnosis.getName(), diagnosis.getFundament()) > 0;
    }

    public Boolean saveAll(List<Diagnosis> diagnoses) {
        String sql = "INSERT INTO diagnosis (name, fundament) VALUES (?, ?)";
        return jdbcTemplate.batchUpdate(sql, diagnoses, diagnoses.size(), (ps, diagnosis) -> {
            ps.setString(1, diagnosis.getName());
            ps.setString(2, diagnosis.getFundament());
        }).length > 0;
    }

    public List<Diagnosis> findAll() {
        String sql = "SELECT * FROM diagnosis";
        return jdbcTemplate.query(sql, new DiagnosisMapper());
    }

    public Diagnosis findById(Integer id) {
        String sql = "SELECT * FROM diagnosis WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new DiagnosisMapper(), id);
    }
}
