package com.dhanush.sapinventoryservice.modal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
@Data
@Schema(description = "Product entity representing an item in the inventory")
public class Product {
    @Id
    @Schema(description = "Unique product identifier", example = "101")
    private Long id;

    @Schema(description = "Name of the product", example = "Laptop")
    private String name;

    @Schema(description = "Price of the product", example = "999.99")
    private Double price;

    @Schema(description = "Available quantity in stock", example = "50")
    private Integer quantity;

    @Schema(description = "URL of the product image", example = "https://example.com/images/laptop.png")
    private String imgUrl;
}
