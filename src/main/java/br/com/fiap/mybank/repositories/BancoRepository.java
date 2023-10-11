package br.com.fiap.mybank.repositories;

import br.com.fiap.mybank.models.BancoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<BancoModel, Long> {
}
