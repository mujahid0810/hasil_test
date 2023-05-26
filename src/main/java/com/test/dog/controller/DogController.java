package com.test.dog.controller;

import com.test.dog.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consume")
public class DogController {

    @Autowired
    private DogService dogService;


    @GetMapping("/cache/update")
    public Object updateCache(){
        return dogService.updateCache();
    }

    @GetMapping("/dogs/list")
    public Object getDog(){
        return dogService.getAllDog();
    }

    @GetMapping("/dog/{breed}/list")
    public Object getDog(@PathVariable String breed){
        return dogService.getBreedList(breed);
    }

    @GetMapping("/dog/{breed}/images")
    public Object getDogImage(@PathVariable String breed){
        return dogService.getDogImage(breed,"");
    }

    @GetMapping("/dog/{breed}/{subBreed}/images")
    public Object getDogSubImage(@PathVariable String breed, @PathVariable(required = true) String subBreed){
        return dogService.getDogImage(breed,subBreed);
    }
}
