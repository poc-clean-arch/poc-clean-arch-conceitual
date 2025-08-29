package br.com.pupposoft.poc.cleanarch.conceitual.infra.web.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Infracao;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.InfracaoGateway;

@Component
public class InfrancaoSistemaExtenoGateway implements InfracaoGateway {

	@Override
	public List<Infracao> obterPorCpf(String cpf) {
		// TODO Implementar
		
		//Obtem dados do sistema externo
		
		return Arrays.asList();
	}

}
