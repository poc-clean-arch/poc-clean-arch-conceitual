package br.com.pupposoft.poc.cleanarch.conceitual.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Infracao {
	private Long id;
	private LocalDateTime dataHora;
	private String descricao;
	private BigDecimal valor;
}
