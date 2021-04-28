package br.com.convert;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.dto.ClienteDTO;
import br.com.dto.ContaDTO;
import br.com.entity.Cliente;
import br.com.entity.Conta;



@Component
public class Converter {

	@Bean
	public ModelMapper model() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper;
	}

	public Cliente convertToCliente(ClienteDTO clienteDTO) {
		return model().map(clienteDTO, Cliente.class);

	}

	public ClienteDTO convertToClienteDTO(Cliente cliente) {
		return model().map(cliente, ClienteDTO.class);
	}

	
	public Conta convertToConta(ContaDTO contaDTO) {
		return model().map(contaDTO, Conta.class);
	}
	
	public ContaDTO convertToContaDTO(Conta conta) {
		return model().map(conta, ContaDTO.class);
	}
	

}
