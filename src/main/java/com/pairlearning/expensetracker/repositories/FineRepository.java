package com.pairlearning.expensetracker.repositories;

import com.pairlearning.expensetracker.domain.Fine;
import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface FineRepository {

    List<Fine> findAll(Integer userId, Integer carId);

    Fine findById(Integer userId, Integer carId, Integer fineId) throws EtResourceNotFoundException;

    Integer create(Integer userId, Integer carId, Double finePrice, String note, String fineCategory, Long fineDate) throws EtBadRequestException;

    void update(Integer userId, Integer carId, Integer fineId, Fine fine) throws EtBadRequestException;

    void removeById(Integer userId, Integer carId, Integer fineId) throws EtResourceNotFoundException;

}
