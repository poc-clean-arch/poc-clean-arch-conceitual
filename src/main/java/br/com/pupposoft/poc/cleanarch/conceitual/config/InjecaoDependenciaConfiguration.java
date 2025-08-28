package br.com.pupposoft.poc.cleanarch.conceitual.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.InfracaoGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.MotoristaGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.NotificacaoGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.usecase.CriarUsuarioUsecase;
import br.com.pupposoft.poc.cleanarch.conceitual.core.usecase.CriarUsuarioUsecaseImpl;
import br.com.pupposoft.poc.cleanarch.conceitual.core.usecase.ObterCalculadoraMultaUsecase;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InjecaoDependenciaConfiguration {

	private final MotoristaGateway motoritaGateway;
	private final InfracaoGateway infracaoGateway;
	private final NotificacaoGateway notificacaoGateway;	
	
	@Bean
	@Autowired
	@DependsOn("obterCalculadoraMultaUsecase")
	public CriarUsuarioUsecase criarUsuarioUsecase(ObterCalculadoraMultaUsecase obterCalculadoraMultaUsecase) {
		return new CriarUsuarioUsecaseImpl(
				motoritaGateway,
				infracaoGateway,
				obterCalculadoraMultaUsecase,
				notificacaoGateway);
	}

	@Bean
	public ObterCalculadoraMultaUsecase obterCalculadoraMultaUsecase() {
		return new ObterCalculadoraMultaUsecase();
	}
}
