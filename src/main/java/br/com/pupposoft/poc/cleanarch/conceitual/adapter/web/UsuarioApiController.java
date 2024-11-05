package br.com.pupposoft.poc.cleanarch.conceitual.adapter.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pupposoft.poc.cleanarch.conceitual.adapter.web.json.UsuarioJson;
import br.com.pupposoft.poc.cleanarch.conceitual.core.controller.UsuarioController;
import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarUsuarioInputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioApiController {
	
    private UsuarioController usuarioController;
	
	@PostMapping
	public Long criar(@Valid @RequestBody UsuarioJson usuarioJson) {
		return usuarioController.criar(mapToDto(usuarioJson));
	}
	
	private CriarUsuarioInputDto mapToDto(UsuarioJson usuarioJson) {
		return new CriarUsuarioInputDto(
				usuarioJson.getId(), 
				usuarioJson.getNome(), 
				usuarioJson.getCpf(), 
				usuarioJson.getDataNascimento());
	}
}
