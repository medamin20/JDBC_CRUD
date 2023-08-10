package com.persistence.persistenceforelide.dao;

import com.persistence.persistenceforelide.entities.Ping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PingrepositoryImpl implements Pingrepository{

    private static final String INSERT_PING_QUERRY="INSERT INTO PING(id,name,email) values(?,?,?)";
    private static final String UPDATE_PING_BYID_QUERRY="UPDATE PING SET name=?, email=? WHERE ID=?";
    private static final String GET_PING_BY_ID_QUERRY="SELECT * FROM PING WHERE ID=?";
    private static final String DELETE_PING_BY_ID_QUERRY="DELETE FROM PING WHERE ID=?";
    private static final String GET_ALL_PINGS_QUERRY="SELECT * FROM ping";

    @Autowired
    private JdbcTemplate jdbcTemplate ;
    @Override
    public Ping savaPing(Ping ping) {

        jdbcTemplate.update(INSERT_PING_QUERRY,ping.getId(),ping.getName(),ping.getEmail());
        return ping;
    }

    @Override
    public int updatePing(Ping ping) {
        return jdbcTemplate.update(UPDATE_PING_BYID_QUERRY,
                new Object[] { ping.getEmail(), ping.getName(),ping.getId() });
    }

    @Override
    public Ping getPingById(int id) {
        try {
            Ping ping = jdbcTemplate.queryForObject(GET_PING_BY_ID_QUERRY,
                    BeanPropertyRowMapper.newInstance(Ping.class), id);
            return ping;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public String deleteById(int id) {

         jdbcTemplate.update(DELETE_PING_BY_ID_QUERRY,id);
         return "Ping got deleted"+ id ;
    }

    @Override
    public List<Ping> allPings() {
        return jdbcTemplate.query(GET_ALL_PINGS_QUERRY,(rs, rowNum) -> {

            return new Ping(rs.getInt("id"),rs.getString("name"),rs.getString("email"));



        });
    }
}
