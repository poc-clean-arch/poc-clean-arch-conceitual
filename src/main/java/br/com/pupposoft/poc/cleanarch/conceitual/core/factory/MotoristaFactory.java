package br.com.pupposoft.poc.cleanarch.conceitual.core.factory;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Automovel;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Motorista;
import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarMotoristaInputDto;

import java.time.LocalDate;
import java.util.List;

public interface MotoristaFactory {
    Motorista criar(CriarMotoristaInputDto novoMotorista);
    Motorista criar(Long id, String nome, String cpf, LocalDate dataNascimento, List<Automovel> automoveis);
}
