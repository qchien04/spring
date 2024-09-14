package com.example.pro.service;

import com.example.pro.models.User;
import com.example.pro.repo.UserRepo;
import com.example.pro.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service
public class UserService{
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public User save(User customer) {
        return userRepo.save(customer);
    }

    public User findById(int id) {
        Optional<User> result= userRepo.findByUserId(id);
        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new RuntimeException("User not found : "+id);
        }
    }

    public Page<User> findAll(int page,int size) {
        return userRepo.findAll(PageRequest.of(page,size, Sort.by("userId")));
    }

    @Transactional
    public void deleteById(int id) {
        userRepo.deleteById(id);
    }

    public User findByUsername(String username) {
        Optional<User> result= userRepo.findByUsername(username);
        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new RuntimeException("User not found : "+username);
        }
    }

    public PageResponse<User> getPageUsers(int page, int size) {
        List<User> listUser=userRepo.findAll();
        int totalCustomers = listUser.size();

        int start = Math.min(page * size, totalCustomers);
        int end = Math.min((page + 1) * size, totalCustomers);

        List<User> paginatedCustomers = listUser.subList(start, end);

        return new PageResponse<>(paginatedCustomers, page, size, totalCustomers);
    }





}
