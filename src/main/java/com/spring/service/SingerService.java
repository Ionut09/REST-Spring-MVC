package com.spring.service;

import com.spring.domain.Singer;

import java.util.List;


public interface SingerService {
    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(long id);
}