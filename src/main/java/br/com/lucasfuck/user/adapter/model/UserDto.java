package br.com.lucasfuck.user.adapter.model;

import br.com.lucasfuck.user.core.validator.Adulthood;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public record UserDto(Long id, @CPF String cpf, @Email String email,
                      @NotBlank String name, @Adulthood LocalDate birthDate) {
}
