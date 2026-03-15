package com.dhanush.sapinventoryservice.modal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Standard API response containing status and optional error details")
public class ResponseDTO {

    @Schema(description = "Result status of the operation", example = "SUCCESS")
    private Status status;

    @Schema(description = "Error details, present only when status is FAILURE")
    private ErrorBlock error;

    public ResponseDTO(Status status) {
        this.status = status;
    }
}
