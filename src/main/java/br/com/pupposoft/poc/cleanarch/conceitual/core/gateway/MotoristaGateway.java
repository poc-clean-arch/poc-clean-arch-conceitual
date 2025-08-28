package br.com.pupposoft.poc.cleanarch.conceitual.core.gateway;

import java.util.Optional;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Motorista;

public interface MotoristaGateway {

	Optional<Motorista> obterPorCpf(String cpf);

	Long criar(Motorista usuario);

}
