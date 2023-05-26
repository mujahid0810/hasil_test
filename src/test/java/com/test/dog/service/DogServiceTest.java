package com.test.dog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dog.dto.DogListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DogService.class, ObjectMapper.class})
public class DogServiceTest {

    @Autowired
    private DogService dogService;

    @MockBean
    private DogTransformService dogTransformService;

    @MockBean
    private CallDogApi callDogApi;

    @MockBean
    private ValueOperations<String, Object> redisOps;

    private final DogListDto dogListDto = DogListDto.builder()
            .message(new String[]{"dog"})
            .status("success")
            .build();

    @Test
    public void getAllDog_success(){
        when(redisOps.get(any())).thenReturn(dogListDto);
        var response = dogService.getAllDog();
        assertNotNull(response);
    }
}
