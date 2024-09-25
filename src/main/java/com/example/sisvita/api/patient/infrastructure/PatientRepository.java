package com.example.sisvita.api.patient.infrastructure;

import com.example.sisvita.api.patient.domain.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PatientRepository {

    private final JdbcTemplate jdbcTemplate;

    public PatientRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class PatientRowMapper implements RowMapper<Patient> {
        @Override
        public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setIdUser(rs.getInt("id_user"));
            return patient;
        }
    }

    public Boolean save(Patient patient) {
        String sql = "INSERT INTO patient (id_user) VALUES (?)";
        return jdbcTemplate.update(sql, patient.getIdUser()) > 0;
    }

    public Patient findById(Integer id) {
        String sql = "SELECT * FROM patient WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PatientRowMapper(), id);
    }

    public List<Patient> findAll() {
        String sql = "SELECT * FROM patient";
        return jdbcTemplate.query(sql, new PatientRowMapper());
    }

    public Integer findIdByIdUser(Integer idUser) {
        String sql = "SELECT p.id FROM Patient p WHERE p.user.id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, idUser);
    }
}
