package br.com.entity.listener;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.PrePersist;

import br.com.entity.Extrato;

public class ExtratoListener {

	@PrePersist
	protected void onCreate(Extrato extrato) {

		LocalDateTime hoje = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		extrato.setDataTransacao(hoje);
	}
}
