package br.com.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoTransacao {
	TRANSFERENCIA("Transferência"), SAQUE("Saque"), DEPOSITO("Deposito");

	private String tipo;
}
