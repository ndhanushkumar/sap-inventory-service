package com.dhanush.sapinventoryservice.util;

import com.dhanush.sapinventoryservice.modal.Product;
import com.dhanush.sapinventoryservice.modal.ResponseDTO;
import com.dhanush.sapinventoryservice.modal.Status;
import reactor.core.publisher.Mono;

public class UtilFunctions {

    public static Mono<ResponseDTO> toDTO(Product item){
        return Mono.just(new ResponseDTO( Status.SUCCESS));

    }
}
