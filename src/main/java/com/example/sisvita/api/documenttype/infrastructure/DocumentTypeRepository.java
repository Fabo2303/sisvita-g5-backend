package com.example.sisvita.api.documenttype.infrastructure;

import com.example.sisvita.api.documenttype.domain.DocumentType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DocumentTypeRepository {

    private final JdbcTemplate jdbcTemplate;

    public DocumentTypeRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class DocumentTypeRowMapper implements RowMapper<DocumentType> {
        @Override
        public DocumentType mapRow(ResultSet rs, int rowNum) throws SQLException {
            DocumentType documentType = new DocumentType();
            documentType.setId(rs.getInt("id"));
            documentType.setType(rs.getString("type"));
            documentType.setLength(rs.getInt("length"));
            return documentType;
        }
    }

    public Boolean save(DocumentType documentType) {
        String sql = "INSERT INTO document_type (type, length) VALUES (?, ?)";
        return jdbcTemplate.update(sql, documentType.getType(), documentType.getLength()) > 0;
    }

    public List<DocumentType> findAll() {
        String sql = "SELECT * FROM document_type";
        return jdbcTemplate.query(sql, new DocumentTypeRowMapper());
    }

    public DocumentType findById(Integer id) {
        String sql = "SELECT * FROM document_type WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new DocumentTypeRowMapper(), id);
    }

    public DocumentType findByType(String type) {
        String sql = "SELECT * FROM document_type WHERE type = ?";
        return jdbcTemplate.queryForObject(sql, new DocumentTypeRowMapper(), type);
    }
}
