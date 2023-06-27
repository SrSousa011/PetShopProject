package com.api.petshop.dtos;


import com.api.petshop.domain.Address;
import java.time.LocalDate;
import java.util.Set;

public record ClientRecordDto(
        long client_id,
        String name,
        String cpf,
        LocalDate date_register,
        Set<Address> address
) {
}
