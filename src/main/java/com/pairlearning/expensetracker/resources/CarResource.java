package com.pairlearning.expensetracker.resources;

import com.pairlearning.expensetracker.domain.Car;
import com.pairlearning.expensetracker.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cars")
public class CarResource {

    @Autowired
    CarService carService;

    @GetMapping("")
    public ResponseEntity<List<Car>> getAllCars(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        List<Car> cars = carService.fetchAllCars(userId);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCarById(HttpServletRequest request,
                                                    @PathVariable("carId") Integer carId) {
        int userId = (Integer) request.getAttribute("userId");
        Car car = carService.fetchCarById(userId, carId);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Car> addCar(HttpServletRequest request,
                                                @RequestBody Map<String, Object> carMap) {
        int userId = (Integer) request.getAttribute("userId");
        String title = (String) carMap.get("title");
        String description = (String) carMap.get("description");
        Car car = carService.addCar(userId, title, description);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<Map<String, Boolean>> updateCar(HttpServletRequest request,
                                                               @PathVariable("carId") Integer carId,
                                                               @RequestBody Car car) {
        int userId = (Integer) request.getAttribute("userId");
        carService.updateCar(userId, carId, car);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Map<String, Boolean>> deleteCar(HttpServletRequest request,
                                                               @PathVariable("carId") Integer carId) {
        int userId = (Integer) request.getAttribute("userId");
        carService.removeCarWithAllFines(userId, carId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
