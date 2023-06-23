package com.api.petshop.dtos;

public record ContactRecordDto(
        long contact_id,
        String email,
        ClientRecordDto client
) {
}
