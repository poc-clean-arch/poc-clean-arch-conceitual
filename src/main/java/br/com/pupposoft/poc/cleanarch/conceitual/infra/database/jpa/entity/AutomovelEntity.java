package br.com.pupposoft.poc.cleanarch.conceitual.infra.database.jpa.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Automovel")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutomovelEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String modelo;
	
	private LocalDate dataModelo;
	
	@ManyToOne
	private MotoristaEntity motorista;
}
