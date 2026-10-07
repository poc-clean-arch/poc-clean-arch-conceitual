package br.com.pupposoft.poc.cleanarch.conceitual.core.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CriarMotoristaInputDto {
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private List<Long> automoveisIds;
}
