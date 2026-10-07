package br.com.pupposoft.poc.cleanarch.conceitual.infra.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pupposoft.poc.cleanarch.conceitual.core.controller.UsuarioController;
import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarMotoristaInputDto;
import br.com.pupposoft.poc.cleanarch.conceitual.infra.web.json.UsuarioJson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("motoristas")
@RequiredArgsConstructor
public class UsuarioApi {
	
    private UsuarioController usuarioController;
    
	@PostMapping
	public Long criar(@Valid @RequestBody UsuarioJson usuarioJson) {
		return  usuarioController.criar(mapToDto(usuarioJson)).getId();
	}
	
	private CriarMotoristaInputDto mapToDto(UsuarioJson usuarioJson) {
		return new CriarMotoristaInputDto(
				usuarioJson.getId(), 
				usuarioJson.getNome(), 
				usuarioJson.getCpf(), 
				usuarioJson.getDataNascimento(),
				usuarioJson.getAutomoveisIds());
	}
}
