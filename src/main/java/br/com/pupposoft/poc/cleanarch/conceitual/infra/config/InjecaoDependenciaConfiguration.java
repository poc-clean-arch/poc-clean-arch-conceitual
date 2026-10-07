package br.com.pupposoft.poc.cleanarch.conceitual.infra.config;

import br.com.pupposoft.poc.cleanarch.conceitual.core.factory.MotoristaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.InfracaoGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.MotoristaGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.NotificacaoGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.usecase.CriarUsuarioUsecase;
import br.com.pupposoft.poc.cleanarch.conceitual.core.usecase.CriarMotoristaUsecaseImpl;
import br.com.pupposoft.poc.cleanarch.conceitual.core.usecase.ObterCalculadoraMultaUsecase;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InjecaoDependenciaConfiguration {

	private final MotoristaGateway motoritaGateway;
	private final InfracaoGateway infracaoGateway;
	private final NotificacaoGateway notificacaoGateway;
	private final MotoristaFactory motoristaFactory;
	
	@Bean
	@Autowired
	@DependsOn("obterCalculadoraMultaUsecase")
	public CriarUsuarioUsecase criarUsuarioUsecase(ObterCalculadoraMultaUsecase obterCalculadoraMultaUsecase) {
		return new CriarMotoristaUsecaseImpl(
				motoritaGateway,
				infracaoGateway,
				obterCalculadoraMultaUsecase,
				notificacaoGateway,
				motoristaFactory);
	}

	@Bean
	public ObterCalculadoraMultaUsecase obterCalculadoraMultaUsecase() {
		return new ObterCalculadoraMultaUsecase();
	}

}
