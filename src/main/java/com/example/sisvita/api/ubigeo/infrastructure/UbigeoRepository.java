package com.example.sisvita.api.ubigeo.infrastructure;

import com.example.sisvita.api.ubigeo.domain.Ubigeo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UbigeoRepository {

    private final JdbcTemplate jdbcTemplate;

    public UbigeoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class UbigeoRowMapper implements RowMapper<Ubigeo> {
        @Override
        public Ubigeo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ubigeo ubigeo = new Ubigeo();
            ubigeo.setUbigeo(rs.getString("ubigeo"));
            ubigeo.setDepartment(rs.getString("department"));
            ubigeo.setProvince(rs.getString("province"));
            ubigeo.setDistrict(rs.getString("district"));
            ubigeo.setLongitude(rs.getDouble("longitude"));
            ubigeo.setLatitude(rs.getDouble("latitude"));
            return ubigeo;
        }
    }

    public List<String> findProvincesByDepartment(String department){
        String sql = "SELECT DISTINCT province FROM ubigeo WHERE department = ?";
        return jdbcTemplate.queryForList(sql, String.class, department);
    }

    public List<String> findDistrictsByDepartmentAndProvince(String department, String province){
        String sql = "SELECT DISTINCT district FROM ubigeo WHERE department = ? AND province = ?";
        return jdbcTemplate.queryForList(sql, String.class, department, province);
    }

    public Ubigeo findUbigeoByDepartmentAndProvinceAndDistrict(String department, String province, String district){
        String sql = "SELECT * FROM ubigeo WHERE department = ? AND province = ? AND district = ?";
        return jdbcTemplate.queryForObject(sql, new UbigeoRowMapper(), department, province, district);
    }

    public List<String> findAllDepartments(){
        String sql = "SELECT DISTINCT department FROM ubigeo";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<Ubigeo> findAll(){
        String sql = "SELECT * FROM ubigeo";
        return jdbcTemplate.query(sql, new UbigeoRowMapper());
    }

    public Boolean save(Ubigeo ubigeo){
        String sql = "INSERT INTO ubigeo (ubigeo, department, province, district, longitude, latitude) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, ubigeo.getUbigeo(), ubigeo.getDepartment(), ubigeo.getProvince(), ubigeo.getDistrict(), ubigeo.getLongitude(), ubigeo.getLatitude()) > 0;
    }

    public Ubigeo findByUbigeo(String ubigeo){
        String sql = "SELECT * FROM ubigeo WHERE ubigeo = ?";
        return jdbcTemplate.queryForObject(sql, new UbigeoRowMapper(), ubigeo);
    }
}
