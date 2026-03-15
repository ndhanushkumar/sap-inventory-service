package com.dhanush.sapinventoryservice.modal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Error details returned when an operation fails")
public class ErrorBlock {
    @Schema(description = "Originating service namespace", example = "sap-inventory-service", accessMode = Schema.AccessMode.READ_ONLY)
    private final String name_space = "sap-inventory-service";

    @Schema(description = "Human-readable error message", example = "Inventory Check Failure")
    private String message;

    @Schema(description = "Error code identifier", example = "1001")
    private String id;
}
