package com.example.sisvita.api.consignment.infrastructure;

import com.example.sisvita.api.consignment.domain.Consignment;
import com.example.sisvita.api.consignment.dto.ConsignmentResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConsignmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public ConsignmentRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class ConsignmentMapper implements RowMapper<Consignment> {
        @Override
        public Consignment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Consignment consignment = new Consignment();
            consignment.setId(rs.getInt("id"));
            consignment.setDate(rs.getTimestamp("date"));
            consignment.setObservation(rs.getString("observation"));
            consignment.setFundament(rs.getString("fundament"));
            consignment.setIdPatient(rs.getInt("id_patient"));
            consignment.setIdSpecialist(rs.getInt("id_specialist"));
            consignment.setIdResolvedTest(rs.getInt("id_resolved_test"));
            consignment.setIdDiagnosis(rs.getInt("id_diagnosis"));
            consignment.setIdTreatment(rs.getInt("id_treatment"));
            return consignment;
        }
    }

    private static final class ConsignmentResponseMapper implements RowMapper<ConsignmentResponse> {
        @Override
        public ConsignmentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            ConsignmentResponse consignmentResponse = new ConsignmentResponse();
            consignmentResponse.setDate(rs.getTimestamp("date"));
            consignmentResponse.setObservation(rs.getString("observation"));
            consignmentResponse.setFundament(rs.getString("fundament"));
            consignmentResponse.setNameSpecialist(rs.getString("name_specialist"));
            consignmentResponse.setNameDiagnosis(rs.getString("name_diagnosis"));
            consignmentResponse.setFundamentDiagnosis(rs.getString("fundament_diagnosis"));
            consignmentResponse.setNameTreatment(rs.getString("name_treatment"));
            consignmentResponse.setFundamentTreatment(rs.getString("fundament_treatment"));
            return consignmentResponse;
        }
    }

    public Consignment findById(int id){
        String sql = "SELECT * FROM consignment WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ConsignmentMapper(), id);
    }

    public List<Consignment> findAll(){
        String sql = "SELECT * FROM consignment";
        return jdbcTemplate.query(sql, new ConsignmentMapper());
    }

    public ConsignmentResponse findConsignmentResponseByIdResolvedTest(int idResolvedTest){
        String sql = "SELECT c.date, c.observation, c.fundament, s.name AS name_specialist, d.name AS name_diagnosis, d.fundament AS fundament_diagnosis, t.name AS name_treatment, t.fundament AS fundament_treatment FROM consignment c " +
                "JOIN specialist s ON c.id_specialist = s.id " +
                "JOIN diagnosis d ON c.id_diagnosis = d.id " +
                "JOIN treatment t ON c.id_treatment = t.id " +
                "WHERE c.id_resolved_test = ?";
        return jdbcTemplate.queryForObject(sql, new ConsignmentResponseMapper(), idResolvedTest);
    }

    public Consignment findByPatientIdAndResolvedTestId(int idPatient, int idResolvedTest){
        String sql = "SELECT * FROM consignment WHERE id_patient = ? AND id_resolved_test = ? ORDER BY date DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new ConsignmentMapper(), idPatient, idResolvedTest);
    }

    @Transactional
    public Boolean save(Consignment consignment){
        String sql = "INSERT INTO consignment (date, observation, fundament, id_patient, id_specialist, id_resolved_test, id_diagnosis, id_treatment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, consignment.getDate(), consignment.getObservation(), consignment.getFundament(), consignment.getIdPatient(), consignment.getIdSpecialist(), consignment.getIdResolvedTest(), consignment.getIdDiagnosis(), consignment.getIdTreatment()) > 0;
    }

    public Consignment findLastConsignment(Integer idPatient, Integer idResolvedTest) {
        String sql = "SELECT * FROM consignment WHERE id_patient = ? AND id_resolved_test = ? ORDER BY date DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new ConsignmentMapper(), idPatient, idResolvedTest);
    }
}
