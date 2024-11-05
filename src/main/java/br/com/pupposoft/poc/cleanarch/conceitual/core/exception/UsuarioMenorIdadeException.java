package br.com.pupposoft.poc.cleanarch.conceitual.core.exception;

import lombok.Getter;

@Getter
public class UsuarioMenorIdadeException extends SystemBaseException {
	private static final long serialVersionUID = 6198217850168561979L;
	
	private final String code = "usuario.menorIdade";//NOSONAR
	private final String message = "Usuário é menor de idade.";//NOSONAR
	private final Integer httpStatus = 422;//NOSONAR
}
