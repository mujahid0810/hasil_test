package com.test.dog.service;

import com.test.dog.dto.DogListDto;
import com.test.dog.dto.ListImageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CallDogApi {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplate restTemplateOther;

    @Value("${dog.api.base.url}")
    private String baseUrl;

    private static final String BASE_BREED = "/breed/";

    public DogListDto listAllBreeds(){
        try {
            ResponseEntity<DogListDto> responseEntity = restTemplate.getForEntity(
                    baseUrl+"/breeds/list/all",DogListDto.class);
            log.info("response list all breeds: {}",responseEntity.getBody());
            return responseEntity.getBody();
        }catch (Exception e){
            log.info(e.getMessage());
            throw e;
        }
    }

    public ListImageDto getDogImages(String breed, String subBreed){
        String url;
        if(subBreed.isEmpty()){
            url = baseUrl+ BASE_BREED + breed +"/images";
        }else {
            url = baseUrl+ BASE_BREED + breed +"/"+ subBreed +"/images";
        }
        try {
            ResponseEntity<ListImageDto> responseEntity = restTemplateOther.getForEntity(
                    url,ListImageDto.class);
            log.info("response dog images: {}",responseEntity.getBody());
            return responseEntity.getBody();
        }catch (Exception e){
            log.info(e.getMessage());
            throw e;
        }
    }

    public ListImageDto getBreadList(String breed){
        String url = baseUrl+ BASE_BREED +breed+"/list";
        try {
            ResponseEntity<ListImageDto> responseEntity = restTemplate.getForEntity(
                    url,ListImageDto.class);
            log.info("response bread list: {}",responseEntity.getBody());
            return responseEntity.getBody();
        }catch (Exception e){
            log.info(e.getMessage());
            throw e;
        }
    }
}
