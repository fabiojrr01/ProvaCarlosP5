package br.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dto.ClienteDTO;
import br.com.entity.Cliente;
import br.com.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/salvar-cliente/", method = RequestMethod.POST)
	public ResponseEntity<Cliente> salvarCliente(@RequestBody ClienteDTO clienteDTO) {
		return new ResponseEntity<>(clienteService.salvar(clienteDTO), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/buscar-cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable Long id) {
		return new ResponseEntity<>(clienteService.buscarPorId(id), HttpStatus.OK);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/deletar-cliente/{id}", method = RequestMethod.DELETE)
	public void deletarCliente(@PathVariable Long id) {
		clienteService.excluir(id);
	}

	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable ("id") Long id, @RequestBody ClienteDTO clienteDTO) {
		return new ResponseEntity<>(clienteService.editar(clienteDTO, id), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/listar-Todos-clientes/", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listaClientes(){
		return new ResponseEntity<>(clienteService.listaDeCliente(),HttpStatus.OK);
	}
	
}
