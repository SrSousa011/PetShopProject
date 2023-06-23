package com.api.petshop.dtos;


import com.api.petshop.domain.Address;

import java.util.List;

public record ClientRecordDto(
        long client_id,
        String name,
        List<Address> addresses
) {
}
