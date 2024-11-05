package br.com.pupposoft.poc.cleanarch.conceitual.core.exception;

import lombok.Getter;

@Getter
public class UsuarioExistenteException extends SystemBaseException {
	private static final long serialVersionUID = 6198217850168561979L;
	
	private final String code = "usuario.usuarioJaExiste";//NOSONAR
	private final String message = "Usuário já existe.";//NOSONAR
	private final Integer httpStatus = 422;//NOSONAR
}
