package com.api.petshop.dtos;


public record ClientRecordDto(
        long client_id,
        String name,

        long addressId
) {
}
