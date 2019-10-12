package com.spring.repository;

import com.spring.domain.Singer;

import java.util.List;

public interface SingerRepository {


    void delete(long id);

    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    Singer findById(Long id);

    Singer save(Singer singer);
}
