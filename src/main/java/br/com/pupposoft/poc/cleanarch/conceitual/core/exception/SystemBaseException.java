package br.com.pupposoft.poc.cleanarch.conceitual.core.exception;

public abstract class SystemBaseException extends RuntimeException {
	private static final long serialVersionUID = 3899886033254816856L;

	public abstract String getCode();
	public abstract Integer getHttpStatus();
	@Override
	public abstract String getMessage();

}