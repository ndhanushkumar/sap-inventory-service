package com.dhanush.sapinventoryservice.service;

import com.dhanush.sapinventoryservice.exception.InventoryException;
import com.dhanush.sapinventoryservice.util.UtilFunctions;
import com.dhanush.sapinventoryservice.modal.Product;
import com.dhanush.sapinventoryservice.modal.RequestDTO;
import com.dhanush.sapinventoryservice.modal.ResponseDTO;
import com.dhanush.sapinventoryservice.modal.Status;
import com.dhanush.sapinventoryservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Flux<Product> addProduct(List<Product> items){
        return productRepository.saveAll(items);

    }

    public Flux<Product> retrieveAllItems(){
        return productRepository.findAll();

    }
    public Mono<ResponseDTO> blockInventory(RequestDTO requestDTO){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return productRepository.findById(requestDTO.getId())
                .filter(item -> item.getQuantity()>= requestDTO.getQuantity())
                .flatMap(item -> {
                    item.setQuantity(item.getQuantity()- requestDTO.getQuantity());
                    return productRepository.save(item);
                }).flatMap(UtilFunctions::toDTO)
                .defaultIfEmpty(new ResponseDTO(Status.FAILURE));
    }

    public Mono<ResponseDTO> inventoryCheck(List<RequestDTO> requestDTO){
//        try {
//            Thread.sleep(1000);
//        }
//        catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
      return Flux.fromIterable(requestDTO)
                .flatMap(item-> this.checkInventory(item.getQuantity(),productRepository.findById(item.getId())))
                .collectList()
                .map(list-> {
                    if(list.contains(Status.FAILURE))
                        return new ResponseDTO(Status.FAILURE);
                    return new ResponseDTO(Status.SUCCESS);
                });

    }
    private Mono<Status> checkInventory(Integer qunatity,Mono<Product> item){
        return item.map(i-> {
            if(i.getQuantity()>=qunatity){
                return Status.SUCCESS;
            }
            try {
                throw  new InventoryException("Inventory Check Failure");
            } catch (InventoryException e) {
                throw new RuntimeException(e);
            }
        });


    }
    public Mono<ResponseDTO> releaseInventory(RequestDTO requestDTO){
        return productRepository.findById(requestDTO.getId())

                .flatMap(item -> {
                    item.setQuantity(item.getQuantity()+ requestDTO.getQuantity());
                    return productRepository.save(item);
                }).flatMap(UtilFunctions::toDTO);

    }
}
