package com.pairlearning.expensetracker.services;

import com.pairlearning.expensetracker.domain.Car;
import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;
import com.pairlearning.expensetracker.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> fetchAllCars(Integer userId) {
        return carRepository.findAll(userId);
    }

    @Override
    public Car fetchCarById(Integer userId, Integer carId) throws EtResourceNotFoundException {
        return carRepository.findById(userId, carId);
    }

    @Override
    public Car addCar(Integer userId, String title, String description) throws EtBadRequestException {
        int carId = carRepository.create(userId, title, description);
        return carRepository.findById(userId, carId);
    }

    @Override
    public void updateCar(Integer userId, Integer carId, Car car) throws EtBadRequestException {
        carRepository.update(userId, carId, car);
    }

    @Override
    public void removeCarWithAllFines(Integer userId, Integer carId) throws EtResourceNotFoundException {
        this.fetchCarById(userId, carId);
        carRepository.removeById(userId, carId);
    }
}
