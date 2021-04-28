package br.com.dto;

import lombok.Data;

@Data
public class ClienteDTO {

	private Long id;
	private String cpfCnpj;
	private String telefone;
	private String endereco;
	private String email;
}
