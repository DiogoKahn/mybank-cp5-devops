package br.com.fiap.mybank.controller;

import br.com.fiap.mybank.dtos.BancoDto;
import br.com.fiap.mybank.models.BancoModel;
import br.com.fiap.mybank.services.BancoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Banco", description = "API para o gerenciamento de bancos no sistema")
@RestController
@RequestMapping("/banco")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BancoController extends GenericController{

    final BancoService bancoService;

    public BancoController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @Operation(summary = "Lista todos os usuarios", description = "Lista todos os usuarios do sistema")
    @ApiResponse(responseCode = "200", description = "Usuarios encontrados com sucesso",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BancoModel.class))))
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return ResponseEntity.status(HttpStatus.OK).body(bancoService.getAll());
    }

    @Operation(summary = "Recupera um banco por ID", description = "Recupera os dados de um banco a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BancoModel.class)))
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<BancoModel> getById(@PathVariable Long id) {
        Optional<BancoModel> optionalBanco = bancoService.findById(id);
        return optionalBanco.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salva o usuario", description = "Salva o usuario")
    @ApiResponse(responseCode = "201", description = "Usuario salvo com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BancoModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody BancoDto bancoDto, BindingResult result){
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(bancoService.adicionaBanco(bancoDto));

        } catch (Exception e) {
            return handleErrors(e);
        }
    }

    @Operation(summary = "Exclui um usuario pelo Id" , description = "Exclui um usuario a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "Usuario excluido com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Optional<BancoModel> optionalBanco = bancoService.findById(id);
        return optionalBanco
                .map(banco -> {
                    try {
                        bancoService.deleteById(id);
                        return ResponseEntity.noContent().build();
                    } catch (Exception e) {
                        return handleErrors(e);
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Altera um usuario pelo Id" , description = "Altera um usuario a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Usuario alterado com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @Valid @RequestBody BancoDto bancoDto, BindingResult result){
        Optional<BancoModel> optionalBanco = bancoService.findById(id);
        if (optionalBanco.isEmpty())
                return ResponseEntity.notFound().build();
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(bancoService.putBanco(bancoDto, id));
        } catch (Exception e) {
            return handleErrors(e);
        }
    }


}
