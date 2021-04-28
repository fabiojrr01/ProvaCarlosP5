package br.com.dto;

import lombok.Data;

@Data
public class TransferenciaDTO {

	private String numeroContaOrigem;
	private String agenciaOrigem;
	private String numeroContaDestino;
	private String agenciaDestino;
	private double valor;
}
