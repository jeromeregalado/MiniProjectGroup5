package com.example.MiniProjectGroup5.service;

import com.example.MiniProjectGroup5.enums.CommunityType;
import com.example.MiniProjectGroup5.enums.UserType;
import com.example.MiniProjectGroup5.exception.RecordNotFoundException;
import com.example.MiniProjectGroup5.model.Employee;
import com.example.MiniProjectGroup5.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAllUsers(Pageable pageable);

    User findUserById(Long userId) throws RecordNotFoundException;

    public Page<User> findByRole(UserType userType, Pageable pageable) throws RecordNotFoundException;
}
