package com.pairlearning.expensetracker.repositories;

import com.pairlearning.expensetracker.domain.Fine;
import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class FineRepositoryImpl implements FineRepository {

    private static final String SQL_FIND_ALL = "SELECT FINE_ID, CAR_ID, USER_ID, FINE_PRICE, NOTE, FINE_CATEGORY, FINE_DATE FROM ET_FINES WHERE USER_ID = ? AND CAR_ID = ?";
    private static final String SQL_FIND_BY_ID = "SELECT FINE_ID, CAR_ID, USER_ID, FINE_PRICE, NOTE, FINE_CATEGORY, FINE_DATE FROM ET_FINES WHERE USER_ID = ? AND CAR_ID = ? AND FINE_ID = ?";
    private static final String SQL_CREATE = "INSERT INTO ET_FINES (FINE_ID, CAR_ID, USER_ID, FINE_PRICE, NOTE, FINE_CATEGORY, FINE_DATE) VALUES(NEXTVAL('ET_FINES_SEQ'), ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE ET_FINES SET FINE_PRICE = ?, NOTE = ?, FINE_CATEGORY = ?, FINE_DATE = ? WHERE USER_ID = ? AND CAR_ID = ? AND FINE_ID = ?";
    private static final String SQL_DELETE = "DELETE FROM ET_FINES WHERE USER_ID = ? AND CAR_ID = ? AND FINE_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Fine> findAll(Integer userId, Integer carId) {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{userId, carId}, fineRowMapper);
    }

    @Override
    public Fine findById(Integer userId, Integer carId, Integer fineId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, carId, fineId}, fineRowMapper);
        }catch (Exception e) {
            throw new EtResourceNotFoundException("Fine not found");
        }
    }

    @Override
    public Integer create(Integer userId, Integer carId, Double finePrice, String note, String fineCategory, Long fineDate) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, carId);
                ps.setInt(2, userId);
                ps.setDouble(3, finePrice);
                ps.setString(4, note);
                ps.setString(5, fineCategory);
                ps.setLong(6, fineDate);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("FINE_ID");
        }catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer userId, Integer carId, Integer fineId, Fine fine) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{fine.getFinePrice(), fine.getNote(), fine.getFineCategory(), fine.getFineDate(), userId, carId, fineId});
        }catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void removeById(Integer userId, Integer carId, Integer fineId) throws EtResourceNotFoundException {
        int count = jdbcTemplate.update(SQL_DELETE, new Object[]{userId, carId, fineId});
        if(count == 0)
            throw new EtResourceNotFoundException("Fine not found");
    }

    private RowMapper<Fine> fineRowMapper = ((rs, rowNum) -> {
        return new Fine(rs.getInt("FINE_ID"),
                rs.getInt("CAR_ID"),
                rs.getInt("USER_ID"),
                rs.getDouble("FINE_PRICE"),
                rs.getString("NOTE"),
                rs.getString("FINE_CATEGORY"),
                rs.getLong("FINE_DATE"));
    });
}
