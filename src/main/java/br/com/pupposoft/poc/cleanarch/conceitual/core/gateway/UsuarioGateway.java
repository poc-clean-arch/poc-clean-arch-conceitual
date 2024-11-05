package br.com.pupposoft.poc.cleanarch.conceitual.core.gateway;

import java.util.Optional;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Usuario;

public interface UsuarioGateway {

	Optional<Usuario> obterPorCpf(String cpf);

	Long criar(Usuario usuario);

}
