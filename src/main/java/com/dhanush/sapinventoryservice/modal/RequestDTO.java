package com.dhanush.sapinventoryservice.modal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Request payload for inventory operations")
public class RequestDTO {
    @Schema(description = "Product ID to operate on", example = "101")
    private Long id;

    @Schema(description = "Quantity to check, block, or release", example = "5")
    private Integer quantity;
}
