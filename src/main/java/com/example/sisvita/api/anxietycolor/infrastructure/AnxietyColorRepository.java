package com.example.sisvita.api.anxietycolor.infrastructure;

import com.example.sisvita.api.anxietycolor.domain.AnxietyColor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AnxietyColorRepository {

    private final JdbcTemplate jdbcTemplate;

    public AnxietyColorRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class AnxietyColorRowMapper implements RowMapper<AnxietyColor> {
        @Override
        public AnxietyColor mapRow(ResultSet rs, int rowNum) throws SQLException {
            AnxietyColor anxietyColor = new AnxietyColor();
            anxietyColor.setId(rs.getInt("id"));
            anxietyColor.setColor(rs.getString("color"));
            return anxietyColor;
        }
    }

    public AnxietyColor findById(int id){
        String sql = "SELECT * FROM anxiety_color WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new AnxietyColorRowMapper(), id);
    }

    public List<AnxietyColor> findAll(){
        String sql = "SELECT * FROM anxiety_color";
        return jdbcTemplate.query(sql, new AnxietyColorRowMapper());
    }

    public Boolean save(AnxietyColor anxietyColor){
        String sql = "INSERT INTO anxiety_color (color) VALUES (?)";
        return jdbcTemplate.update(sql, anxietyColor.getColor()) > 0;
    }
}
