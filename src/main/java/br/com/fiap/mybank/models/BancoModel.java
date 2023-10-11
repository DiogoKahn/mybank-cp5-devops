package br.com.fiap.mybank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_banco")
public class BancoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 40)
    private String nome;

    @Column(nullable = false)
    private Float saldo;


    @Column(length = 3)
    private Integer nCartoes;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk_banco_usuario"))
    @JsonIgnore
    private UsuarioModel usuario;
}
