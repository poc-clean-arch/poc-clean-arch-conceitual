package br.com.pupposoft.poc.cleanarch.pratico.core.gateway;

import java.util.List;

import br.com.pupposoft.poc.cleanarch.pratico.core.domain.Infracao;

public interface InfracaoGateway {
	List<Infracao> obterPorCpf(String cpf);
}
