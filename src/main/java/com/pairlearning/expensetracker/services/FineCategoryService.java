package com.pairlearning.expensetracker.services;

import com.pairlearning.expensetracker.domain.FineCategory;
import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface FineCategoryService {

    List<FineCategory> fetchAllFineCategories();

    Optional<FineCategory> fetchFineCategoryById(String fineCategoryId) throws EtResourceNotFoundException;

    FineCategory addFineCategory(String fineCategoryID, Integer userId, String title, String description) throws EtBadRequestException;

    void updateFineCategory(Integer userId, String fineCategoryId, FineCategory fineCategory) throws EtBadRequestException;

    void removeFineCategory(Integer userId, String fineCategoryId) throws EtResourceNotFoundException;

    void removeAllFineCategories(Integer userId) throws EtResourceNotFoundException;

}
