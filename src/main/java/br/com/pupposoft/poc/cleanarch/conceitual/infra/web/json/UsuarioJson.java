package br.com.pupposoft.poc.cleanarch.conceitual.infra.web.json;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioJson {
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String cpf;

	@NotNull
	private LocalDate dataNascimento;
}
