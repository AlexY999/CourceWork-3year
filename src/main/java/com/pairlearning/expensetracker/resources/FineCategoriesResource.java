package com.pairlearning.expensetracker.resources;

import com.pairlearning.expensetracker.domain.FineCategory;
import com.pairlearning.expensetracker.services.FineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/fineCategories")
public class FineCategoriesResource {
    @Autowired
    FineCategoryService fineCategoryService;

    @GetMapping("")
    public ResponseEntity<List<FineCategory>> getAllFineCategories(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        List<FineCategory> fineCategories = fineCategoryService.fetchAllFineCategories();
        return new ResponseEntity<>(fineCategories, HttpStatus.OK);
    }

    @GetMapping("/{fineCategoryId}")
    public ResponseEntity<Optional<FineCategory>> getFineCategoryById(HttpServletRequest request,
                                                          @PathVariable("fineCategoryId") String fineCategoryId) {
        int userId = (Integer) request.getAttribute("userId");
        Optional<FineCategory> fineCategories = fineCategoryService.fetchFineCategoryById(fineCategoryId);
        return new ResponseEntity<>(fineCategories, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<FineCategory> addFineCategory(HttpServletRequest request,
                                      @RequestBody Map<String, Object> carMap) {
        int userId = (Integer) request.getAttribute("userId");
        String title = (String) carMap.get("title");
        String description = (String) carMap.get("description");
        String fineCategoryID = (String) carMap.get("fineCategoryID");

        FineCategory fineCategory = fineCategoryService.addFineCategory(fineCategoryID,userId, title, description);
        return new ResponseEntity<>(fineCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{fineCategoryId}")
    public ResponseEntity<Map<String, Boolean>> updateOrAddCar(HttpServletRequest request,
                                                          @PathVariable("fineCategoryId") String fineCategoryId,
                                                          @RequestBody Map<String, Object> carMap) {
        int userId = (Integer) request.getAttribute("userId");
        String title = (String) carMap.get("title");
        String description = (String) carMap.get("description");

        FineCategory fineCategory = new FineCategory(fineCategoryId, userId, title, description);
        fineCategoryService.updateFineCategory(userId, fineCategoryId, fineCategory);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Map<String, Boolean>> deleteCar(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        fineCategoryService.removeAllFineCategories(userId);
        Map<String, Boolean> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{fineCategoryId}")
    public ResponseEntity<Map<String, Boolean>> deleteCar(HttpServletRequest request,
                                                          @PathVariable("fineCategoryId") String fineCategoryId) {
        int userId = (Integer) request.getAttribute("userId");
        fineCategoryService.removeFineCategory(userId, fineCategoryId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
