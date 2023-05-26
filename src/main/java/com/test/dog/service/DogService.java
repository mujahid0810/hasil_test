package com.test.dog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dog.dto.DogListDto;
import com.test.dog.exception.DataDogNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DogService {

    @Autowired
    private DogTransformService dogTransformService;

    @Autowired
    private CallDogApi callDogApi;

    @Autowired
    private ValueOperations<String, Object> redisOps;

    public Object updateCache(){
        DogListDto dogListDto = callDogApi.listAllBreeds();
        ObjectMapper m = new ObjectMapper();
        Map<String, List<String>> mapData = m.convertValue(Objects.requireNonNull(dogListDto).getMessage(),Map.class);
        dogTransformService.changeKey(mapData, "sheepdog");
        dogTransformService.changeKeyWithValue(mapData, "terrier");
        dogTransformService.updateShiba(mapData);
        mapData =  mapData.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        redisOps.set("dog",mapData);
        return mapData;
    }

    public Object getAllDog(){
        Map<String, List<String>> dogs = (Map<String, List<String>>) redisOps.get("dog");
        if (dogs==null) {
            throw  new DataDogNotFound();
        }
        return dogs;
    }

    public Object getBreedList(String breed){
        return callDogApi.getBreadList(breed);
    }

    public Object getDogImage(String breed, String subBreed){
        return callDogApi.getDogImages(breed,subBreed);
    }
}
