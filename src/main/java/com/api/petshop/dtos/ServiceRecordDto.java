package com.api.petshop.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRecordDto {
    //@NotEmpty(message = "Name is required")
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    //@NotNull(message = "Date is required")
    private LocalDate date;

    private Long pet_id;
}
