package com.test.dog.controller;

import com.test.dog.dto.DogDto;
import com.test.dog.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/portal")
public class PortalController {

    @Autowired
    public PortalService portalService;

    @GetMapping("/getAll")
    public Object getAll(){
        return portalService.getAll();
    }

    @GetMapping("/get/{id}")
    public Object getById(@PathVariable Long id){
        return portalService.getById(id);
    }

    @PostMapping("/create")
    public Object create(@Valid @RequestBody DogDto dogDto){
        return portalService.create(dogDto);
    }

    @PutMapping("/update/{id}")
    public Object update(@Valid @RequestBody DogDto dogDto, @PathVariable Long id){
        return portalService.update(dogDto,id);
    }

    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable Long id){
        return portalService.deleteById(id);
    }

}
