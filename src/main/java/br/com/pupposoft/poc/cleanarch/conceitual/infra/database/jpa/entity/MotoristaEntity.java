package br.com.pupposoft.poc.cleanarch.conceitual.infra.database.jpa.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Motorista")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MotoristaEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private String nome;
	private LocalDate dataNascimento;
	private Double totalMultas;

	@OneToMany
	private List<AutomovelEntity> automoveis;
}
