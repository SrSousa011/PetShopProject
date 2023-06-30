package com.api.petshop.dtos;

import com.api.petshop.domain.Address;
import com.api.petshop.domain.Client;
import com.api.petshop.domain.Race;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetRecordDto {
    private Long pet_id;
    private String name;
    private Client owner;
    private Set<Race> race;
}
