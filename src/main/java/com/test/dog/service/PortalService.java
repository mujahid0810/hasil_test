package com.test.dog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dog.dto.DogDto;
import com.test.dog.exception.DataDogNotFound;
import com.test.dog.model.Dog;
import com.test.dog.repository.DogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PortalService {

    @Autowired
    private DogRepository dogRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public Object getAll(){
        return dogRepository.findAll();
    }

    public Dog getById(Long id){
        Optional<Dog> dogOptional = dogRepository.findById(id);
        return dogOptional.orElseThrow(DataDogNotFound::new);
    }

    public Object create(DogDto dogDto){
        Dog dog = mapper.convertValue(dogDto,Dog.class);
        return dogRepository.save(dog);
    }

    public Object update(DogDto dogDto,Long id){
        getById(id);
        Dog dog = mapper.convertValue(dogDto,Dog.class);
        return dogRepository.save(dog);
    }

    public Object deleteById(Long id){
        getById(id);
        try {
            dogRepository.deleteById(id);
            return "delete success";
        }catch (Exception e){
            log.info("error delete: {}",e.getMessage());
            throw e;
        }
    }
}
