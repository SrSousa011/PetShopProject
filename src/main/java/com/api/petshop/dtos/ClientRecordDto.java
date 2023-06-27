package com.api.petshop.dtos;


import com.api.petshop.domain.Address;
import com.api.petshop.domain.Client;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record ClientRecordDto(
        long client_id,
        String name,
        String cpf,
        LocalDate date_register,
        List<AddressRecordDto> address
) {

    public Client toEntity() {
        List<Address> addresses = address.stream()
                .map(AddressRecordDto::toEntity)
                .collect(Collectors.toList());

        return new Client(client_id, name, cpf, date_register, addresses);
    }
}
