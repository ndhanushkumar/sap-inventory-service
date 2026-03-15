package com.dhanush.sapinventoryservice.service;

import com.dhanush.sapinventoryservice.modal.RequestDTO;
import com.dhanush.sapinventoryservice.modal.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Test
    void inventoryCheck() {
        List<RequestDTO> list=new ArrayList<>();
        list.add(new RequestDTO(1L,1));
        list.add(new RequestDTO(2L,3));
        Mono<ResponseDTO> responseDTOMono =productService.inventoryCheck(list).log();
        StepVerifier.create(responseDTOMono)
                .expectNextCount(1)
                .verifyComplete();


    }
}