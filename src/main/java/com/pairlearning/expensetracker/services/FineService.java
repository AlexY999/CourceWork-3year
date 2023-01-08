package com.pairlearning.expensetracker.services;

import com.pairlearning.expensetracker.domain.Fine;
import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface FineService {

    List<Fine> fetchAllFines(Integer userId, Integer carId);

    Fine fetchFineById(Integer userId, Integer carId, Integer fineId) throws EtResourceNotFoundException;

    Fine addFine(Integer userId, Integer carId, Double finePrice, String note, String fineCategory, Long fineDate) throws EtBadRequestException;

    void updateFine(Integer userId, Integer carId, Integer fineId, Fine fine) throws EtBadRequestException;

    void removeFine(Integer userId, Integer carId, Integer fineId) throws EtResourceNotFoundException;

}
