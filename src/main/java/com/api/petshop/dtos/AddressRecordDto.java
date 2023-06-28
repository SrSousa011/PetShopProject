package com.api.petshop.dtos;

import com.api.petshop.domain.Client;

public record AddressRecordDto(
        long address_Id,
        String street,
        String city,
        String state,
        String postalCode,
        String country,
        Client client_id
) {
}
