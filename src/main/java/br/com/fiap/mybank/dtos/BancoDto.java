package br.com.fiap.mybank.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BancoDto {

    @Schema(example = "Nubank")
    private String nome;

    @Schema(example = "100")
    @NotNull(message = "O saldo é obrigatório")
    @Min(value = 0, message = "O saldo deve ser maior ou igual a 0")
    private Float saldo;

    @Schema(example = "3")
    private Integer nCartoes;

    @Schema(example = "1")
    @NotNull(message = "O Id do Usuario é obrigatório")
    private Long idUsuario;
}
