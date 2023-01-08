package com.pairlearning.expensetracker.services;

import com.pairlearning.expensetracker.domain.User;
import com.pairlearning.expensetracker.exceptions.EtAuthException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

    User fetchUserById(int userId) throws EtResourceNotFoundException;
}
