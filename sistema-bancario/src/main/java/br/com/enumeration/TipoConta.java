package br.com.enumeration;

import lombok.Getter;


@Getter
public enum TipoConta {

	CORRENTE(1, "Conta Corrente"), POUPANCA(2, "Conta Poupança");

	private String tipo;
	private Integer id;

	public static TipoConta carregarPorId(Integer id) {

		for (TipoConta tipo : TipoConta.values()) {
			if (tipo.getId().equals(id)) {
				return tipo;
			}
		}
		return null;
	}

	private TipoConta(Integer id, String tipo) {
		this.tipo = tipo;
		this.id = id;
	}
	
}
