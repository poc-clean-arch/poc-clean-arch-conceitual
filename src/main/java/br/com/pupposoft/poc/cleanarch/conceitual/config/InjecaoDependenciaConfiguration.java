package br.com.pupposoft.poc.cleanarch.conceitual.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.MotoristaGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.usecase.CriarUsuarioUsecase;
import br.com.pupposoft.poc.cleanarch.conceitual.core.usecase.CriarUsuarioUsecaseImpl;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InjecaoDependenciaConfiguration {

	private MotoristaGateway usuarioGateway;
	
	@Bean
	public CriarUsuarioUsecase criarUsuarioUsecase() {
		return new CriarUsuarioUsecaseImpl(usuarioGateway);
	}

	//Criar demais usecases, etc
	
}
