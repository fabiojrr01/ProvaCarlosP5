package br.com.dto;

import lombok.Data;

@Data
public class SaqueDepositoDTO {

	private String agencia;
	private String numeroConta;
	private double valor;
}
