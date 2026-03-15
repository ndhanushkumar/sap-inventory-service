package com.dhanush.sapinventoryservice.controller;

import com.dhanush.sapinventoryservice.modal.Product;
import com.dhanush.sapinventoryservice.modal.RequestDTO;
import com.dhanush.sapinventoryservice.modal.ResponseDTO;
import com.dhanush.sapinventoryservice.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("sap-inventory")
@CrossOrigin
@Tag(name = "Product Inventory", description = "APIs for managing SAP product inventory")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(
            summary = "Add items to inventory",
            description = "Adds one or more products to the inventory. Each product must include an id, name, price, quantity, and imgUrl."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Items added successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("add-item")
    public Flux<Product> addItem(@RequestBody List<Product> items) {
        return productService.addProduct(items);
    }

    @Operation(
            summary = "Get all inventory items",
            description = "Retrieves all products currently stored in the inventory."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all items",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("get-all-items")
    public Flux<Product> getAllItems() {
        return productService.retrieveAllItems();
    }

    @Operation(
            summary = "Block inventory for an order",
            description = "Blocks (reserves) a specified quantity of a product for an order. "
                    + "Reduces the available quantity by the requested amount. "
                    + "Returns FAILURE if the product is not found or insufficient quantity is available."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory blocked successfully or failure response",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PostMapping("inventory-block")
    public Mono<ResponseDTO> blockInventory(@RequestBody RequestDTO requestDTO) {
        return productService.blockInventory(requestDTO);
    }

    @Operation(
            summary = "Check inventory availability",
            description = "Checks whether the requested quantities are available for a list of products. "
                    + "Returns SUCCESS if all items have sufficient stock, FAILURE if any item is insufficient."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory check result",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PostMapping("inventory-check")
    public Mono<ResponseDTO> checkInventory(@RequestBody List<RequestDTO> requestDTO) {
        return productService.inventoryCheck(requestDTO);
    }

    @Operation(
            summary = "Void/release inventory",
            description = "Releases previously blocked inventory for a product. "
                    + "Increases the available quantity by the specified amount (e.g., when an order is cancelled)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory released successfully",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PostMapping("void-sap-items")
    public Mono<ResponseDTO> voidItems(@RequestBody RequestDTO requestDTO) {
        return productService.releaseInventory(requestDTO);
    }
}
