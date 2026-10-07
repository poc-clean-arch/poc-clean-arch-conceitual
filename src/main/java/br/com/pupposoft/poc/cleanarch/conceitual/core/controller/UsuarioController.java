package br.com.pupposoft.poc.cleanarch.conceitual.core.controller;

import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarMotoristaInputDto;
import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarMotoristaOutputDto;
import br.com.pupposoft.poc.cleanarch.conceitual.core.usecase.CriarUsuarioUsecase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioController {

	private final CriarUsuarioUsecase criarUsuarioUsecase;

/*

TODO - Analisar

2. Onde fica o mapeamento de saída/erro?

Não vejo Presenter, nem Response Model, nem tratamento visível de exceção de negócio (o que acontece se
criarUsuarioUsecase.criar(...) lançar algo tipo "motorista menor de idade" ou "CPF duplicado",que o README menciona
como regra?).

Isso pode estar resolvido em outro lugar do projeto (ex: um @ControllerAdvice/@ExceptionHandler global do Spring
capturando exceções de domínio e convertendo pra HTTP 4xx) — que é uma solução válida e comum,
só que desloca a responsabilidade "erro de negócio → código de protocolo" pra um componente de infra centralizado,
em vez de estar no UsuarioController do core. Não é errado, mas é bom ser uma decisão consciente, não um esquecimento.

-> o problema de usar o '@ControllerAdvice/@ExceptionHandler' é que o core controller fica dependente do framework

 */

	public CriarMotoristaOutputDto criar(CriarMotoristaInputDto criarUsuarioInputDto) {
		//FIXME: tratar e retornar exceções de core?
		return criarUsuarioUsecase.criar(criarUsuarioInputDto);
	}
}
