package com.pairlearning.expensetracker.services;

import com.pairlearning.expensetracker.domain.FineCategory;
import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;
import com.pairlearning.expensetracker.repositories.FineCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FineCategoryServiceImpl implements FineCategoryService {

    @Autowired
    FineCategoryRepository fineCategoryRepository;

    @Override
    public List<FineCategory> fetchAllFineCategories() {
        return fineCategoryRepository.findAll();
    }

    @Override
    public Optional<FineCategory> fetchFineCategoryById(String fineCategoryId) throws EtResourceNotFoundException {
        return fineCategoryRepository.findById(fineCategoryId);
    }

    @Override
    public FineCategory addFineCategory(String fineCategoryID, Integer userId, String title, String description) throws EtBadRequestException {
        return fineCategoryRepository.save(new FineCategory(fineCategoryID, userId, title, description));
    }

    @Override
    public void updateFineCategory(Integer userId, String fineCategoryId, FineCategory fineCategory) throws EtBadRequestException {
        fineCategoryRepository.save(fineCategory);
    }

    @Override
    public void removeFineCategory(Integer userId, String fineCategoryId) throws EtResourceNotFoundException {
        fineCategoryRepository.deleteById(fineCategoryId);
    }

    @Override
    public void removeAllFineCategories(Integer userId) throws EtResourceNotFoundException {
        fineCategoryRepository.deleteAll();
    }
}
