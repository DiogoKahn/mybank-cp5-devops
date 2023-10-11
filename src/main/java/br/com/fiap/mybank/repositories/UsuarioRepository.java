package br.com.fiap.mybank.repositories;

import br.com.fiap.mybank.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    List<UsuarioModel> findByemailContainingIgnoreCase (String email);

}