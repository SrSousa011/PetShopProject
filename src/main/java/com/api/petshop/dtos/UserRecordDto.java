package com.api.petshop.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDto(@NotBlank String username, @NotNull String cpf) {

}
