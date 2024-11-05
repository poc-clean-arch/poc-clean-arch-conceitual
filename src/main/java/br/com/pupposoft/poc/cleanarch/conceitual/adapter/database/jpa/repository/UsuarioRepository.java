package br.com.pupposoft.poc.cleanarch.conceitual.adapter.database.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pupposoft.poc.cleanarch.conceitual.adapter.database.jpa.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

	Optional<UsuarioEntity> findByCpf(String cpf);

}
