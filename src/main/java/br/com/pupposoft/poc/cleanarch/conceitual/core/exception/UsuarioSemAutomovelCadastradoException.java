package br.com.pupposoft.poc.cleanarch.conceitual.core.exception;

import lombok.Getter;

@Getter
public class UsuarioSemAutomovelCadastradoException extends SystemBaseException {
	private static final long serialVersionUID = 6198217850168561979L;
	
	private final String code = "usuario.semAutomovel";//NOSONAR
	private final String message = "Usuário sem automovel cadastrado.";//NOSONAR
	private final Integer httpStatus = 422;//NOSONAR
}
