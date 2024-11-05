package br.com.pupposoft.poc.cleanarch.conceitual.core.exception;

import lombok.Getter;

@Getter
public class UsuarioComAutomovelAntigoException extends SystemBaseException {
	private static final long serialVersionUID = -4165947155519237792L;
	
	private final String code = "usuario.comAutomovelAntigo";//NOSONAR
	private final String message = "Usuário com automovel antigo.";//NOSONAR
	private final Integer httpStatus = 422;//NOSONAR
}
