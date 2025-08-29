package br.com.pupposoft.poc.cleanarch.conceitual.infra.queue;

import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Motorista;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.NotificacaoGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificacaoMockGateway implements NotificacaoGateway {

	@Override
	public void notificarRisco(Motorista motorista) {
		log.warn("### MOCK DE NOTIFICAÇÃO ###");
	}

}
