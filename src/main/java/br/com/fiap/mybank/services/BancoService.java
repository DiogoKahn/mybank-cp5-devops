package br.com.fiap.mybank.services;

import br.com.fiap.mybank.dtos.BancoDto;
import br.com.fiap.mybank.models.BancoModel;
import br.com.fiap.mybank.models.UsuarioModel;
import br.com.fiap.mybank.repositories.BancoRepository;
import br.com.fiap.mybank.repositories.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BancoService extends EntityService<BancoModel>{

    private final BancoRepository bancoRepository;
    private final UsuarioRepository usuarioRepository;


    BancoService(JpaRepository<BancoModel, Long> repository, BancoRepository bancoRepository, UsuarioRepository usuarioRepository) {
        super(repository);
        this.bancoRepository = bancoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public BancoModel adicionaBanco(BancoDto bancoDto) {
        try {
            Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(bancoDto.getIdUsuario());
            if(usuarioOptional.isPresent()){
                UsuarioModel usu = usuarioOptional.get();
                BancoModel ban = new BancoModel();
                BeanUtils.copyProperties(bancoDto, ban);
                ban.setUsuario(usu);
                return repository.save(ban);
            } else {
                throw new Exception("Banco não encontrado");
            }
        } catch (Exception e){
            log.error("Erro ao copiar as propriedades do DTO para o modelo de Banco: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar o novo banco.");
        }
    }

    public BancoModel putBanco(BancoDto bancoDto, Long id){
        try {
            Optional<BancoModel> bancoOptional = bancoRepository.findById(id);
            if (bancoOptional.isPresent()){
                BancoModel end = bancoOptional.get();
                BeanUtils.copyProperties(bancoDto, end);

                log.info("Atualizando banco de ID: " + id);
                return repository.save(end);
            } else {
                throw new RuntimeException("Erro ao atualizar o banco.");
            }
        } catch (Exception e) {
            log.error("Erro ao copiar as propriedades do DTO para o modelo de Banco: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar o banco.");
        }
    }

}