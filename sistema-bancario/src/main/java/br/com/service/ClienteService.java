package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.convert.Converter;
import br.com.dto.ClienteDTO;
import br.com.entity.Cliente;
import br.com.exception.ErroException;
import br.com.exception.Mensagens;
import br.com.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteService;
	
	@Autowired
	private Converter convert;
	
	public Cliente salvar(ClienteDTO clienteDTO) {
		Cliente cliente = convert.convertToCliente(clienteDTO);
		return clienteService.save(cliente);
	}
	
	public ClienteDTO buscarPorId(Long id) {
		Cliente cliente = clienteService.getOne(id);
		if(cliente != null) {
			return convert.convertToClienteDTO(cliente);
		}
		throw new ErroException(Mensagens.CLIENTE_INVALIDO);
	}
	
	public void excluir(Long id) {
		if(id != null) {
			clienteService.deleteById(id);
		}
		throw new ErroException(Mensagens.CLIENTE_INEXISTENTE);
	}
	
	@SuppressWarnings("unused")
	public Cliente editar(ClienteDTO clienteDTO , Long id) {
		clienteDTO.setId(id);
		if(clienteDTO != null) {
			Cliente cliente = convert.convertToCliente(clienteDTO);
			return clienteService.save(cliente);
		}
		throw new  ErroException(Mensagens.CLIENTE_INVALIDO);
	}
	
	public List<Cliente> listaDeCliente(){
		
		if(clienteService.findAll() != null) {
			return clienteService.findAll();
		}
		throw new ErroException(Mensagens.LISTA_DE_CLIENTES_VAZIA);
	}
}
