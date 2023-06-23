package com.api.petshop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRecordDto(
        long address_Id,
        String street,
        String city,
        String state,
        String postalCode,
        String country,
        long clientIds
) {
}
