package br.com.pupposoft.poc.cleanarch.conceitual.core.domain;

import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarMotoristaInputDto;
import br.com.pupposoft.poc.cleanarch.conceitual.core.factory.MotoristaFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//TODO: analizar: este factory está aqui somente para conseguir ter acesso aos 'builders' (protected) das entities de
// dominio. Mas não achei a melhor opção..
public class MotoristaFactoryImpl implements MotoristaFactory {

    @Override
    public Motorista criar(CriarMotoristaInputDto criarMotoristaInputDto) {

        if(criarMotoristaInputDto.getCpf() == null ||  criarMotoristaInputDto.getCpf().isEmpty()){
            throw new IllegalArgumentException("O CPF deve ser preenchido!");
        }
        //TODO: adicionar demais regras de criação

        return Motorista.builder()
                .id(criarMotoristaInputDto.getId())
                .nome(criarMotoristaInputDto.getNome())
                .cpf(criarMotoristaInputDto.getCpf())
                .dataNascimento(criarMotoristaInputDto.getDataNascimento())
                .automoveis(criarMotoristaInputDto.getAutomoveisIds().stream().map(id -> Automovel.builder().id(id).build()).toList())
                .ativo(true)
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    @Override
    public Motorista criar(Long id, String nome, String cpf, LocalDate dataNascimento, List<Automovel> automoveis) {
        //FIXME: Implementar
        return null;
    }

}
