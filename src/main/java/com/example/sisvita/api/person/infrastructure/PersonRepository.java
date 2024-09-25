package com.example.sisvita.api.person.infrastructure;

import com.example.sisvita.api.person.domain.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonRepository {

    private final JdbcTemplate jdbcTemplate;

    public PersonRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class PersonRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setDocument(rs.getString("document"));
            person.setName(rs.getString("name"));
            person.setLastName(rs.getString("last_name"));
            person.setSecondLastName(rs.getString("second_last_name"));
            person.setIdGender(rs.getInt("id_gender"));
            person.setIdDocumentType(rs.getInt("id_document_type"));
            person.setBirthDate(rs.getDate("birth_date"));
            person.setEmail(rs.getString("email"));
            person.setPhone(rs.getString("phone"));
            person.setUbigeo(rs.getString("ubigeo"));

            return person;
        }
    }

    public Boolean save(Person person) {
        String sql = "INSERT INTO person (document, name, last_name, second_last_name, gender, document_type, birth_date, email, phone, ubigeo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, person.getDocument(), person.getName(), person.getLastName(), person.getSecondLastName(), person.getIdGender(), person.getIdDocumentType(), person.getBirthDate(), person.getEmail(), person.getPhone(), person.getUbigeo()) > 0;
    }

    public List<Person> findAll() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, new PersonRowMapper());
    }

    public Person findByDocument(String document) {
        String sql = "SELECT * FROM person WHERE document = ?";
        return jdbcTemplate.queryForObject(sql, new PersonRowMapper(), document);
    }

    public Person findById(Integer id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PersonRowMapper(), id);
    }
}
