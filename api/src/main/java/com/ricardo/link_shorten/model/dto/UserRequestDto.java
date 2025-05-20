package com.ricardo.link_shorten.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class UserRequestDto {
    @NotBlank(message = "Nome do usuário não pode estar vazio!")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres.")
    private String username;

    @NotBlank(message = "Email do usuário não pode estar vazio!")
    @Email(message = "O email deve estar em um formato válido")
    private String email;

    @NotBlank(message = "Senha do usuário não pode estar vazio!")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    private String password;
}
