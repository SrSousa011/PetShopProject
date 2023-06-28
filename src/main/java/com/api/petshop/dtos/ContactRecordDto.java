package com.api.petshop.dtos;

import com.api.petshop.domain.Address;

import java.util.Set;

public record ContactRecordDto(
        long contact_id,
        String email,
        ClientRecordDto client,
        Set<Address> address
) {
}
