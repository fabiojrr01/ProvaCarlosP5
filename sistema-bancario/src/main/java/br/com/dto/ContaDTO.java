package br.com.dto;

import br.com.entity.Cliente;
import lombok.Data;

@Data
public class ContaDTO {

	private Long id;
	private String numeroConta;
	private String agencia;
	private Cliente cliente;
	private Double rendimento;
	private String tipoConta;
	private Double saldo;
}
