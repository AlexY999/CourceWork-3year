package com.pairlearning.expensetracker.repositories;

import com.pairlearning.expensetracker.domain.Car;
import com.pairlearning.expensetracker.domain.FineCategory;
import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FineCategoryRepository extends MongoRepository<FineCategory, String>{

}
