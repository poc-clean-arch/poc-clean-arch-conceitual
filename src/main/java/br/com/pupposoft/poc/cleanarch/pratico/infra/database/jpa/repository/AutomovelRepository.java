package br.com.pupposoft.poc.cleanarch.pratico.infra.database.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pupposoft.poc.cleanarch.pratico.infra.database.jpa.entity.AutomovelEntity;

public interface AutomovelRepository extends JpaRepository<AutomovelEntity, Long> {

	List<AutomovelEntity> findByUsuarioId(Long id);

}
