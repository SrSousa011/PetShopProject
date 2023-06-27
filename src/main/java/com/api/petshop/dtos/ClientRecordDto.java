package com.api.petshop.dtos;


import com.api.petshop.domain.Address;
import com.api.petshop.domain.Client;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record ClientRecordDto(
        long client_id,
        String name,
        String cpf,
        LocalDate date_register,
        Set<Address> address
) {
}
