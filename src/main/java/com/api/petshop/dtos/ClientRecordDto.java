package com.api.petshop.dtos;


import com.api.petshop.domain.Address;
import com.api.petshop.domain.Contact;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Set;

public record ClientRecordDto(
        long client_id,
        String name,
        String cpf,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date_register,
        Set<Address> address,
        Set<Contact> contact
) {
}
