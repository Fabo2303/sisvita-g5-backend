package com.example.sisvita.api.specialist.infrastructure;

import com.example.sisvita.api.specialist.domain.Specialist;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class SpecialistRepository {

    private final JdbcTemplate jdbcTemplate;

    public SpecialistRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class SpecialistRowMapper implements RowMapper<Specialist> {
        @Override
        public Specialist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Specialist specialist = new Specialist();
            specialist.setId(rs.getInt("id"));
            specialist.setLicense(rs.getString("license"));
            specialist.setSpecialty(rs.getString("specialty"));
            specialist.setIdUser(rs.getInt("id_user"));
            return specialist;
        }
    }

    public Boolean save(Specialist specialist) {
        String sql = "INSERT INTO specialists (license, specialty, id_user) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, specialist.getLicense(), specialist.getSpecialty(), specialist.getIdUser()) > 0;
    }

    public Specialist findById(int id) {
        String sql = "SELECT * FROM specialists WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new SpecialistRowMapper(), id);
    }

    public Specialist findByLicense(String license) {
        String sql = "SELECT * FROM specialists WHERE license = ?";
        return jdbcTemplate.queryForObject(sql, new SpecialistRowMapper(), license);
    }

    public Specialist findBySpecialty(String specialty) {
        String sql = "SELECT * FROM specialists WHERE specialty = ?";
        return jdbcTemplate.queryForObject(sql, new SpecialistRowMapper(), specialty);
    }

    public int findIdByIdUser(int idUser) {
        String sql = "SELECT id FROM specialists WHERE id_user = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, idUser);
    }

    public List<Specialist> findAll() {
        String sql = "SELECT * FROM specialists";
        return jdbcTemplate.query(sql, new SpecialistRowMapper());
    }
}
