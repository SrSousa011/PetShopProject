package com.api.petshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaceRecordDto {
    private Long race_id;
    private String name;

    private Long pet_id;
}
