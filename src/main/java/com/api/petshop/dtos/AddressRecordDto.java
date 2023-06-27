package com.api.petshop.dtos;

import com.api.petshop.domain.Address;
import com.api.petshop.domain.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRecordDto(
        long address_Id,
        String street,
        String city,
        String state,
        String postalCode,
        String country,
        ClientRecordDto client
) {

    public Address toEntity() {
        return new Address(street, city, state, postalCode, country);
    }
}
