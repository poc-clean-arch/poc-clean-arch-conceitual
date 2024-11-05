package br.com.pupposoft.poc.cleanarch.conceitual.core.exception;

import lombok.Getter;

@Getter
public class ErroAoAcessarRepositorioException extends SystemBaseException {
	private static final long serialVersionUID = 1184512342688657535L;
	
	private final String code = "usuario.erroAcessarRepositorio";//NOSONAR
	private final String message = "Erro ao acessar repositorio de dados.";//NOSONAR
	private final Integer httpStatus = 500;//NOSONAR
}
