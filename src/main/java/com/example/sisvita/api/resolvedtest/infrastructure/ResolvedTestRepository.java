package com.example.sisvita.api.resolvedtest.infrastructure;

import com.example.sisvita.api.resolvedtest.domain.ResolvedTest;
import com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseHeatMap;
import com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseTableFormat;
import com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseViewPatient;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ResolvedTestRepository {

    private final JdbcTemplate jdbcTemplate;

    public ResolvedTestRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class ResolvedTestRowMapper implements RowMapper<ResolvedTest> {
        @Override
        public ResolvedTest mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResolvedTest resolvedTest = new ResolvedTest();
            resolvedTest.setId(rs.getInt("id"));
            resolvedTest.setDate(rs.getTimestamp("date"));
            resolvedTest.setResult(rs.getInt("result"));
            resolvedTest.setIdTemplateTest(rs.getInt("id_template_test"));
            resolvedTest.setIdClassification(rs.getInt("id_classification"));
            resolvedTest.setIdPatient(rs.getInt("id_patient"));
            resolvedTest.setIdConsignment(rs.getInt("id_consignment"));
            return resolvedTest;
        }
    }

    private static final class ResolvedTestResponseTableFormatMapper implements RowMapper<ResolvedTestResponseTableFormat> {
        @Override
        public ResolvedTestResponseTableFormat mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResolvedTestResponseTableFormat resolvedTestResponseTableFormat = new ResolvedTestResponseTableFormat();
            resolvedTestResponseTableFormat.setId(rs.getInt("id"));
            resolvedTestResponseTableFormat.setNamePatient(rs.getString("namePatient"));
            resolvedTestResponseTableFormat.setLastNamePatient(rs.getString("lastNamePatient"));
            resolvedTestResponseTableFormat.setDate(rs.getTimestamp("date"));
            resolvedTestResponseTableFormat.setNameTemplateTest(rs.getString("nameTemplateTest"));
            resolvedTestResponseTableFormat.setColorClassification(rs.getString("colorClassification"));
            return resolvedTestResponseTableFormat;
        }
    }

    private static final class ResolvedTestResponseHeatMapMapper implements RowMapper<ResolvedTestResponseHeatMap> {
        @Override
        public ResolvedTestResponseHeatMap mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResolvedTestResponseHeatMap resolvedTestResponseHeatMap = new ResolvedTestResponseHeatMap();
            resolvedTestResponseHeatMap.setId(rs.getInt("id"));
            resolvedTestResponseHeatMap.setUbigeo(rs.getString("ubigeo"));
            resolvedTestResponseHeatMap.setDepartment(rs.getString("department"));
            resolvedTestResponseHeatMap.setLatitude(rs.getDouble("latitude"));
            resolvedTestResponseHeatMap.setLongitude(rs.getDouble("longitude"));
            resolvedTestResponseHeatMap.setIntensity(rs.getInt("intensity"));
            resolvedTestResponseHeatMap.setUbigeoIntensityCount(rs.getInt("ubigeoIntensityCount"));
            return resolvedTestResponseHeatMap;
        }
    }

    private static final class ResolvedTestResponseViewPatientMapper implements RowMapper<ResolvedTestResponseViewPatient> {
        @Override
        public ResolvedTestResponseViewPatient mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResolvedTestResponseViewPatient resolvedTestResponseViewPatient = new ResolvedTestResponseViewPatient();
            resolvedTestResponseViewPatient.setId(rs.getInt("id"));
            resolvedTestResponseViewPatient.setDate(rs.getTimestamp("date"));
            resolvedTestResponseViewPatient.setNameTemplateTest(rs.getString("nameTemplateTest"));
            resolvedTestResponseViewPatient.setInterpretation(rs.getString("interpretation"));
            resolvedTestResponseViewPatient.setColorClassification(rs.getString("colorClassification"));
            resolvedTestResponseViewPatient.setResult(rs.getInt("result"));
            return resolvedTestResponseViewPatient;
        }
    }

    public Boolean save(ResolvedTest resolvedTest) {
        String sql = "INSERT INTO resolved_test (date, result, id_template_test, id_classification, id_patient) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, resolvedTest.getDate(), resolvedTest.getResult(), resolvedTest.getIdTemplateTest(), resolvedTest.getIdClassification(), resolvedTest.getIdPatient()) > 0;
    }

    public ResolvedTest findById(Integer id) {
        String sql = "SELECT * FROM resolved_test WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ResolvedTestRowMapper(), id);
    }

    public List<ResolvedTest> findAll() {
        String sql = "SELECT * FROM resolved_test";
        return jdbcTemplate.query(sql, new ResolvedTestRowMapper());
    }

    public List<ResolvedTest> findByIdPatient(Integer idPatient) {
        String sql = "SELECT * FROM resolved_test WHERE id_patient = ?";
        return jdbcTemplate.query(sql, new ResolvedTestRowMapper(), idPatient);
    }

    public List<ResolvedTestResponseViewPatient> findByIdPatientView(Integer idPatient) {
        String sql = "SELECT rt.id, rt.date, tt.name AS nameTemplateTest, c.interpretation, ac.color AS colorClassification, rt.result " +
                "FROM resolved_test rt " +
                "JOIN template_test tt ON rt.id_template_test = tt.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "JOIN anxiety_color ac ON c.id_anxiety_color = ac.id " +
                "WHERE rt.id_patient = ?";
        return jdbcTemplate.query(sql, new ResolvedTestResponseViewPatientMapper(), idPatient);
    }

    public ResolvedTest findTopByPacienteIdOrderByDateDesc(Integer idPatient) {
        String sql = "SELECT * FROM resolved_test WHERE id_patient = ? ORDER BY date DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new ResolvedTestRowMapper(), idPatient);
    }

    public List<ResolvedTestResponseTableFormat> findByTemplateTestName(String name) {
        String sql = "SELECT rt.id, p.name AS namePatient, p.last_name AS lastNamePatient, rt.date, tt.name AS nameTemplateTest, ac.color AS colorClassification " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN template_test tt ON rt.id_template_test = tt.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "JOIN anxiety_color ac ON c.id_anxiety_color = ac.id " +
                "WHERE tt.name = ?";
        return jdbcTemplate.query(sql, new ResolvedTestResponseTableFormatMapper(), name);
    }

    public List<ResolvedTestResponseTableFormat> findByClassificationInterpretation(String interpretation) {
        String sql = "SELECT rt.id, p.name AS namePatient, p.last_name AS lastNamePatient, rt.date, tt.name AS nameTemplateTest, ac.color AS colorClassification " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN template_test tt ON rt.id_template_test = tt.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "JOIN anxiety_color ac ON c.id_anxiety_color = ac.id " +
                "WHERE c.interpretation = ?";
        return jdbcTemplate.query(sql, new ResolvedTestResponseTableFormatMapper(), interpretation);
    }

    public List<ResolvedTestResponseTableFormat> findByTemplateTestNameAndClassificationInterpretation(String name, String interpretation) {
        String sql = "SELECT rt.id, p.name AS namePatient, p.last_name AS lastNamePatient, rt.date, tt.name AS nameTemplateTest, ac.color AS colorClassification " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN template_test tt ON rt.id_template_test = tt.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "JOIN anxiety_color ac ON c.id_anxiety_color = ac.id " +
                "WHERE tt.name = ? AND c.interpretation = ?";
        return jdbcTemplate.query(sql, new ResolvedTestResponseTableFormatMapper(), name, interpretation);
    }

    public List<ResolvedTestResponseTableFormat> findByDateBetween(Date fechaInicio, Date fechaFin) {
        String sql = "SELECT rt.id, p.name AS namePatient, p.last_name AS lastNamePatient, rt.date, tt.name AS nameTemplateTest, ac.color AS colorClassification " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN template_test tt ON rt.id_template_test = tt.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "JOIN anxiety_color ac ON c.id_anxiety_color = ac.id " +
                "WHERE rt.date BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new ResolvedTestResponseTableFormatMapper(), fechaInicio, fechaFin);
    }

    public List<ResolvedTestResponseTableFormat> findByDateBetweenAndTemplateTestName(Date fechaInicio, Date fechaFin, String name) {
        String sql = "SELECT rt.id, p.name AS namePatient, p.last_name AS lastNamePatient, rt.date, tt.name AS nameTemplateTest, ac.color AS colorClassification " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN template_test tt ON rt.id_template_test = tt.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "JOIN anxiety_color ac ON c.id_anxiety_color = ac.id " +
                "WHERE rt.date BETWEEN ? AND ? AND tt.name = ?";
        return jdbcTemplate.query(sql, new ResolvedTestResponseTableFormatMapper(), fechaInicio, fechaFin, name);
    }

    public List<ResolvedTestResponseTableFormat> findByDateBetweenAndClassificationInterpretation(Date fechaInicio, Date fechaFin, String interpretation) {
        String sql = "SELECT rt.id, p.name AS namePatient, p.last_name AS lastNamePatient, rt.date, tt.name AS nameTemplateTest, ac.color AS colorClassification " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN template_test tt ON rt.id_template_test = tt.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "JOIN anxiety_color ac ON c.id_anxiety_color = ac.id " +
                "WHERE rt.date BETWEEN ? AND ? AND c.interpretation = ?";
        return jdbcTemplate.query(sql, new ResolvedTestResponseTableFormatMapper(), fechaInicio, fechaFin, interpretation);
    }

    public List<ResolvedTestResponseTableFormat> findByDateBetweenAndTemplateTestNameAndClassificationInterpretation(Date fechaInicio, Date fechaFin, String name, String interpretation) {
        String sql = "SELECT rt.id, p.name AS namePatient, p.last_name AS lastNamePatient, rt.date, tt.name AS nameTemplateTest, ac.color AS colorClassification " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN template_test tt ON rt.id_template_test = tt.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "JOIN anxiety_color ac ON c.id_anxiety_color = ac.id " +
                "WHERE rt.date BETWEEN ? AND ? AND tt.name = ? AND c.interpretation = ?";
        return jdbcTemplate.query(sql, new ResolvedTestResponseTableFormatMapper(), fechaInicio, fechaFin, name, interpretation);
    }

    public List<ResolvedTestResponseTableFormat> findAllResolvedTestsResponse() {
        String sql = "SELECT rt.id, p.name AS namePatient, p.last_name AS lastNamePatient, rt.date, tt.name AS nameTemplateTest, ac.color AS colorClassification " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN template_test tt ON rt.id_template_test = tt.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "JOIN anxiety_color ac ON c.id_anxiety_color = ac.id";
        return jdbcTemplate.query(sql, new ResolvedTestResponseTableFormatMapper());
    }

    public List<ResolvedTestResponseHeatMap> findAllResolvedTestHeatMap() {
        String sql = "SELECT MIN(rt.id) AS id, p.ubigeo AS ubigeo, p.department AS department, p.latitude AS latitude, p.longitude AS longitude, c.intensity AS intensity, COUNT(rt.id) AS ubigeoIntensityCount " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "GROUP BY p.ubigeo, p.department, p.latitude, p.longitude, c.intensity";
        return jdbcTemplate.query(sql, new ResolvedTestResponseHeatMapMapper());
    }

    public List<ResolvedTestResponseHeatMap> findByDateBetweenHeatMap(Date fechaInicio, Date fechaFin) {
        String sql = "SELECT MIN(rt.id) AS id, p.ubigeo AS ubigeo, p.department AS department, p.latitude AS latitude, p.longitude AS longitude, c.intensity AS intensity, COUNT(rt.id) AS ubigeoIntensityCount " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "WHERE rt.date BETWEEN ? AND ? " +
                "GROUP BY p.ubigeo, p.department, p.latitude, p.longitude, c.intensity";
        return jdbcTemplate.query(sql, new ResolvedTestResponseHeatMapMapper(), fechaInicio, fechaFin);
    }

    public List<ResolvedTestResponseHeatMap> findByDateBetweenAndTemplateTestNameHeatMap(Date fechaInicio, Date fechaFin, String name) {
        String sql = "SELECT MIN(rt.id) AS id, p.ubigeo AS ubigeo, p.department AS department, p.latitude AS latitude, p.longitude AS longitude, c.intensity AS intensity, COUNT(rt.id) AS ubigeoIntensityCount " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "WHERE rt.date BETWEEN ? AND ? AND tt.name = ? " +
                "GROUP BY p.ubigeo, p.department, p.latitude, p.longitude, c.intensity";
        return jdbcTemplate.query(sql, new ResolvedTestResponseHeatMapMapper(), fechaInicio, fechaFin, name);
    }

    public List<ResolvedTestResponseHeatMap> findByDateBetweenAndClassificationInterpretationHeatMap(Date fechaInicio, Date fechaFin, String interpretation) {
        String sql = "SELECT MIN(rt.id) AS id, p.ubigeo AS ubigeo, p.department AS department, p.latitude AS latitude, p.longitude AS longitude, c.intensity AS intensity, COUNT(rt.id) AS ubigeoIntensityCount " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "WHERE rt.date BETWEEN ? AND ? AND c.interpretation = ? " +
                "GROUP BY p.ubigeo, p.department, p.latitude, p.longitude, c.intensity";
        return jdbcTemplate.query(sql, new ResolvedTestResponseHeatMapMapper(), fechaInicio, fechaFin, interpretation);
    }

    public List<ResolvedTestResponseHeatMap> findByDateBetweenAndTemplateTestNameAndClassificationInterpretationHeatMap(Date fechaInicio, Date fechaFin, String name, String interpretation) {
        String sql = "SELECT MIN(rt.id) AS id, p.ubigeo AS ubigeo, p.department AS department, p.latitude AS latitude, p.longitude AS longitude, c.intensity AS intensity, COUNT(rt.id) AS ubigeoIntensityCount " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "WHERE rt.date BETWEEN ? AND ? AND tt.name = ? AND c.interpretation = ? " +
                "GROUP BY p.ubigeo, p.department, p.latitude, p.longitude, c.intensity";
        return jdbcTemplate.query(sql, new ResolvedTestResponseHeatMapMapper(), fechaInicio, fechaFin, name, interpretation);
    }

    public List<ResolvedTestResponseHeatMap> findByTemplateTestNameHeatMap(String name) {
        String sql = "SELECT MIN(rt.id) AS id, p.ubigeo AS ubigeo, p.department AS department, p.latitude AS latitude, p.longitude AS longitude, c.intensity AS intensity, COUNT(rt.id) AS ubigeoIntensityCount " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "WHERE tt.name = ? " +
                "GROUP BY p.ubigeo, p.department, p.latitude, p.longitude, c.intensity";
        return jdbcTemplate.query(sql, new ResolvedTestResponseHeatMapMapper(), name);
    }

    public List<ResolvedTestResponseHeatMap> findByClassificationInterpretationHeatMap(String interpretation) {
        String sql = "SELECT MIN(rt.id) AS id, p.ubigeo AS ubigeo, p.department AS department, p.latitude AS latitude, p.longitude AS longitude, c.intensity AS intensity, COUNT(rt.id) AS ubigeoIntensityCount " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "WHERE c.interpretation = ? " +
                "GROUP BY p.ubigeo, p.department, p.latitude, p.longitude, c.intensity";
        return jdbcTemplate.query(sql, new ResolvedTestResponseHeatMapMapper(), interpretation);
    }

    public List<ResolvedTestResponseHeatMap> findByTemplateTestNameAndClassificationInterpretationHeatMap(String name, String interpretation) {
        String sql = "SELECT MIN(rt.id) AS id, p.ubigeo AS ubigeo, p.department AS department, p.latitude AS latitude, p.longitude AS longitude, c.intensity AS intensity, COUNT(rt.id) AS ubigeoIntensityCount " +
                "FROM resolved_test rt " +
                "JOIN patient p ON rt.id_patient = p.id " +
                "JOIN classification c ON rt.id_classification = c.id " +
                "WHERE tt.name = ? AND c.interpretation = ? " +
                "GROUP BY p.ubigeo, p.department, p.latitude, p.longitude, c.intensity";
        return jdbcTemplate.query(sql, new ResolvedTestResponseHeatMapMapper(), name, interpretation);
    }

    public Boolean updateConsignationId(Integer idConsignation, Integer idTestResolved) {
        String sql = "UPDATE resolved_test SET id_consignation = ? WHERE id = ?";
        return jdbcTemplate.update(sql, idConsignation, idTestResolved) > 0;
    }
}
