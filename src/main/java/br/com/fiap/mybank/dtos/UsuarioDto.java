package br.com.fiap.mybank.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDto {

    @Schema(example = "Diogo Kahn")
    private String nome;

    @Schema(example = "diogo.kahn@gmail.com")
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @Schema(example = "dkahn0610")
    @NotBlank(message = "A senha é obrigatório")
    private String senha;

}
