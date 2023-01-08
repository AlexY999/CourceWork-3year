package com.pairlearning.expensetracker.repositories;

import com.pairlearning.expensetracker.domain.Car;
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
public class CarRepositoryImpl implements CarRepository {

    private static final String SQL_FIND_ALL = "SELECT C.CAR_ID, C.USER_ID, C.TITLE, C.DESCRIPTION, " +
            "COALESCE(SUM(T.FINE_PRICE), 0) TOTAL_EXPENSE " +
            "FROM ET_FINES T RIGHT OUTER JOIN ET_CARS C ON C.CAR_ID = T.CAR_ID " +
            "WHERE C.USER_ID = ? GROUP BY C.CAR_ID";
    private static final String SQL_FIND_BY_ID = "SELECT C.CAR_ID, C.USER_ID, C.TITLE, C.DESCRIPTION, " +
            "COALESCE(SUM(T.FINE_PRICE), 0) TOTAL_EXPENSE " +
            "FROM ET_FINES T RIGHT OUTER JOIN ET_CARS C ON C.CAR_ID = T.CAR_ID " +
            "WHERE C.USER_ID = ? AND C.CAR_ID = ? GROUP BY C.CAR_ID";
    private static final String SQL_CREATE = "INSERT INTO ET_CARS (CAR_ID, USER_ID, TITLE, DESCRIPTION) VALUES(NEXTVAL('ET_CARS_SEQ'), ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE ET_CARS SET TITLE = ?, DESCRIPTION = ? " +
            "WHERE USER_ID = ? AND CAR_ID = ?";
    private static final String SQL_DELETE_CAR = "DELETE FROM ET_CARS WHERE USER_ID = ? AND CAR_ID = ?";
    private static final String SQL_DELETE_ALL_FINES = "DELETE FROM ET_FINES WHERE CAR_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Car> findAll(Integer userId) throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{userId}, carRowMapper);
    }

    @Override
    public Car findById(Integer userId, Integer carId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, carId}, carRowMapper);
        }catch (Exception e) {
            throw new EtResourceNotFoundException("Car not found");
        }
    }

    @Override
    public Integer create(Integer userId, String title, String description) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, title);
                ps.setString(3, description);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("CAR_ID");
        }catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer userId, Integer carId, Car car) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{car.getTitle(), car.getDescription(), userId, carId});
        }catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void removeById(Integer userId, Integer carId) {
        this.removeAllCatFines(carId);
        jdbcTemplate.update(SQL_DELETE_CAR, new Object[]{userId, carId});
    }

    private void removeAllCatFines(Integer carId) {
        jdbcTemplate.update(SQL_DELETE_ALL_FINES, new Object[]{carId});
    }

    private RowMapper<Car> carRowMapper = ((rs, rowNum) -> {
        return new Car(rs.getInt("CAR_ID"),
                rs.getInt("USER_ID"),
                rs.getString("TITLE"),
                rs.getString("DESCRIPTION"),
                rs.getDouble("TOTAL_EXPENSE"));
    });
}
