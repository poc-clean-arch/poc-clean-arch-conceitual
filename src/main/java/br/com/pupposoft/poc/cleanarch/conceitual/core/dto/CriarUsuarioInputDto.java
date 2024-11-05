package br.com.pupposoft.poc.cleanarch.conceitual.core.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CriarUsuarioInputDto {
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
}
