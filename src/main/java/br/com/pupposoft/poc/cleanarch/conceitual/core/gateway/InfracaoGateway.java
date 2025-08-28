package br.com.pupposoft.poc.cleanarch.conceitual.core.gateway;

import java.util.List;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Infracao;

public interface InfracaoGateway {
	List<Infracao> obterPorCpf(String cpf);
}
