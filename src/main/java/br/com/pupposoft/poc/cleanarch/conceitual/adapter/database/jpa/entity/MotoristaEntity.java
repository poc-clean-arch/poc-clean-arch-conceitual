package br.com.pupposoft.poc.cleanarch.conceitual.adapter.database.jpa.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	private List<AutomovelEntity> automoveis;
}
