package com.pairlearning.expensetracker.repositories;

import com.pairlearning.expensetracker.domain.Car;
import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CarRepository {

    List<Car> findAll(Integer userId) throws EtResourceNotFoundException;

    Car findById(Integer userId, Integer carId) throws EtResourceNotFoundException;

    Integer create(Integer userId, String title, String description) throws EtBadRequestException;

    void update(Integer userId, Integer carId, Car car) throws EtBadRequestException;

    void removeById(Integer userId, Integer carId);

}
