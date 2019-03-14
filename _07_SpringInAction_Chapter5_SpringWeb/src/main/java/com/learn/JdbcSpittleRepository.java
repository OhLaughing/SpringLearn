package com.learn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSpittleRepository implements SpittleRepository {

  private JdbcOperations jdbc;

  @Autowired
  public JdbcSpittleRepository(JdbcOperations jdbc) {
    this.jdbc = jdbc;
  }

  public List<Spittle> findRecentSpittles() {
    return jdbc.query(
        "select id, message,  latitude, longitude" +
        " from Spittle" +
        "  limit 20",
        new SpittleRowMapper());
  }

  public List<Spittle> findSpittles(long max, int count) {
    return jdbc.query(
        "select id, message, latitude, longitude" +
        " from Spittle" +
        " where id < ?" +
        "  limit 20",
        new SpittleRowMapper(), max);
  }

  public Spittle findOne(long id) {
    return jdbc.queryForObject(
        "select id, message,  latitude, longitude" +
        " from Spittle" +
        " where id = ?",
        new SpittleRowMapper(), id);
  }

  public void save(Spittle spittle) {
    jdbc.update(
        "insert into Spittle (id, message, latitude, longitude)" +
        " values (?, ?, ?, ?)",
        spittle.getId(),
        spittle.getMessage(),
        spittle.getLatitude(),
        spittle.getLongitude());
  }

  private static class SpittleRowMapper implements RowMapper<Spittle> {
    public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Spittle(
          rs.getLong("id"),
          rs.getString("message"), 
          rs.getDouble("longitude"),
          rs.getDouble("latitude"));
    }
  }
  
}
