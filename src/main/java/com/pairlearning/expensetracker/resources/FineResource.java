package com.pairlearning.expensetracker.resources;

import com.pairlearning.expensetracker.domain.Fine;
import com.pairlearning.expensetracker.services.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cars/{carId}/fines")
public class FineResource {

    @Autowired
    FineService fineService;

    @GetMapping("")
    public ResponseEntity<List<Fine>> getAllFines(HttpServletRequest request,
                                                                @PathVariable("carId") Integer carId) {
        int userId = (Integer) request.getAttribute("userId");
        List<Fine> fines = fineService.fetchAllFines(userId, carId);
        return new ResponseEntity<>(fines, HttpStatus.OK);
    }

    @GetMapping("/{fineId}")
    public ResponseEntity<Fine> getFineById(HttpServletRequest request,
                                                          @PathVariable("carId") Integer carId,
                                                          @PathVariable("fineId") Integer fineId) {
        int userId = (Integer) request.getAttribute("userId");
        Fine fine = fineService.fetchFineById(userId, carId, fineId);
        return new ResponseEntity<>(fine, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Fine> addFine(HttpServletRequest request,
                                                      @PathVariable("carId") Integer carId,
                                                      @RequestBody Map<String, Object> fineMap) {
        int userId = (Integer) request.getAttribute("userId");
        Double finePrice = Double.valueOf(fineMap.get("finePrice").toString());
        String note = (String) fineMap.get("note");
        String fineCategory = (String) fineMap.get("fineCategory");
        Long fineDate = (Long) fineMap.get("fineDate");
        Fine fine = fineService.addFine(userId, carId, finePrice, note, fineCategory, fineDate);
        return new ResponseEntity<>(fine, HttpStatus.CREATED);
    }

    @PutMapping("/{fineId}")
    public ResponseEntity<Map<String, Boolean>> updateFine(HttpServletRequest request,
                                                                  @PathVariable("carId") Integer carId,
                                                                  @PathVariable("fineId") Integer fineId,
                                                                  @RequestBody Fine fine) {
        int userId = (Integer) request.getAttribute("userId");
        fineService.updateFine(userId, carId, fineId, fine);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{fineId}")
    public ResponseEntity<Map<String, Boolean>> deleteFine(HttpServletRequest request,
                                                                  @PathVariable("carId") Integer carId,
                                                                  @PathVariable("fineId") Integer fineId) {
        int userId = (Integer) request.getAttribute("userId");
        fineService.removeFine(userId, carId, fineId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
