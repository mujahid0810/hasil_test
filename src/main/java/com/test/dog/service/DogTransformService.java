package com.test.dog.service;

import com.test.dog.dto.ListImageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DogTransformService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CallDogApi callDogApi;

    public void changeKey(Map<String, List<String>> mapData, String key){
        List<String> sheepdog = mapData.get(key);
        sheepdog.forEach(a->mapData.put(key+"-"+a, List.of()));
        mapData.remove(key);
    }

    public void changeKeyWithValue(Map<String, List<String>> mapData, String key){
        List<String> sheepdog = mapData.get(key);
        sheepdog.forEach(a->{
            ListImageDto listImageDto = callDogApi.getDogImages(key,a);
            mapData.put(key+"-"+a, Objects.requireNonNull(listImageDto).getMessage());
        });
        mapData.remove(key);
    }
    public void updateShiba(Map<String, List<String>> mapData){
        ListImageDto listImageDto = callDogApi.getDogImages("shiba","");
        List<String> listImage = listImageDto.getMessage();
        listImage = listImage.stream()
                .filter(a->a.charAt(a.indexOf(".jpg")-1)%2==0)
                .collect(Collectors.toList());
        mapData.replace("shiba", listImage);
    }
}
