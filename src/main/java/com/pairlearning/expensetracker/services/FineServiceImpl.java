package com.pairlearning.expensetracker.services;

import com.pairlearning.expensetracker.domain.Fine;
import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;
import com.pairlearning.expensetracker.repositories.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FineServiceImpl implements FineService {

    @Autowired
    FineRepository fineRepository;

    @Override
    public List<Fine> fetchAllFines(Integer userId, Integer carId) {
        return fineRepository.findAll(userId, carId);
    }

    @Override
    public Fine fetchFineById(Integer userId, Integer carId, Integer fineId) throws EtResourceNotFoundException {
        return fineRepository.findById(userId, carId, fineId);
    }

    @Override
    public Fine addFine(Integer userId, Integer carId, Double finePrice, String note, String fineCategory, Long fineDate) throws EtBadRequestException {
        int fineId = fineRepository.create(userId, carId, finePrice, note, fineCategory, fineDate);
        return fineRepository.findById(userId, carId, fineId);
    }

    @Override
    public void updateFine(Integer userId, Integer carId, Integer fineId, Fine fine) throws EtBadRequestException {
        fineRepository.update(userId, carId, fineId, fine);
    }

    @Override
    public void removeFine(Integer userId, Integer carId, Integer fineId) throws EtResourceNotFoundException {
        fineRepository.removeById(userId, carId, fineId);
    }
}
