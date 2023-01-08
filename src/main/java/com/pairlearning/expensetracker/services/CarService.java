package com.pairlearning.expensetracker.services;

import com.pairlearning.expensetracker.domain.Car;
import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CarService {

    List<Car> fetchAllCars(Integer userId);

    Car fetchCarById(Integer userId, Integer carId) throws EtResourceNotFoundException;

    Car addCar(Integer userId, String title, String description) throws EtBadRequestException;

    void updateCar(Integer userId, Integer carId, Car car) throws EtBadRequestException;

    void removeCarWithAllFines(Integer userId, Integer carId) throws EtResourceNotFoundException;

}
