package com.spring.controller;

import com.spring.domain.Singer;
import com.spring.service.SingerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/singers")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @GetMapping
    public List<Singer> allSingers() {
        return singerService.findAll();
    }


    //http://localhost:8089/singers/6 unde 6 o sa devin id
    @GetMapping("/{id}")
    public Singer findById(@PathVariable Long id) {
        return singerService.findById(id);
    }



    //http://localhost:8089/singers/filter?firstName=John
    @GetMapping("/filter")
    public List<Singer> findByFirstName(@RequestParam String firstName) {
        return singerService.findByFirstName(firstName);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public Singer create(@RequestBody Singer singer) {
        System.out.println("Creating singer: " + singer);
        singerService.save(singer);
        System.out.println("Singer created successfully with info: " + singer);
        return singer;
    }

    @ResponseStatus(OK)
    @PutMapping(value = "/{id}")
    public void update(@RequestBody Singer singer, @PathVariable Long id) {
        System.out.println("Updating singer: " + singer);
        singerService.save(singer);
        System.out.println("Singer updated successfully with info: " + singer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        System.out.println("Deleting singer with id: " + id);
        singerService.delete(id);
        System.out.println("Singer deleted successfully");
    }
}
