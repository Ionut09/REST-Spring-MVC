package com.spring.repository;

import com.spring.domain.Singer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class SingerRepositoryImpl implements SingerRepository {

    @Autowired(required = true)// required defaults to true
    private JdbcTemplate jdbcTemplate;

    @Override
    public void delete(long id) {
        String deleteStatement = "delete from singer where id =?";
        int updatedRows = jdbcTemplate.update(deleteStatement, id);
    }

    @Override
    public List<Singer> findAll() {
        String sql = "select id, version,  first_name, last_name, birth_date from singer";
        RowMapper<Singer> singerRowMapper = getSingerRowMapper();
        List<Singer> singers = jdbcTemplate.query(sql, singerRowMapper);
        Collections.sort(singers, Comparator.comparing(Singer::getId));
        return singers;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        String sql = "select id, version,  first_name, last_name, birth_date from singer where first_name = ?";
        RowMapper<Singer> singerRowMapper = getSingerRowMapper();
        List<Singer> singers = jdbcTemplate.query(sql, singerRowMapper, firstName);
        Collections.sort(singers, Comparator.comparing(Singer::getId));
        return singers;
    }

    @Override
    public Singer findById(Long id) {
        String sql = "select id, version,  first_name, last_name, birth_date from singer where id = ?";

        return jdbcTemplate.queryForObject(sql, getSingerRowMapper(), id);
    }

    @Override
    public Singer save(Singer singer) {
        String sql = "insert into singer (first_name, version, last_name, birth_date) values (?, ?, ?, ?)";
        Object[] params = new Object[]{singer.getFirstName(),
                singer.getVersion(),
                singer.getLastName(),
                singer.getBirthDate()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params);
        return findAll().stream().max(Comparator.comparing(Singer::getId)).get();
    }


    private RowMapper<Singer> getSingerRowMapper() {
        return (rs, rowNum) -> {
            Singer singer = new Singer();
            singer.setId(rs.getLong("id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setVersion(rs.getInt("version"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(rs.getDate("birth_date").toLocalDate());
            return singer;
        };
    }
}
