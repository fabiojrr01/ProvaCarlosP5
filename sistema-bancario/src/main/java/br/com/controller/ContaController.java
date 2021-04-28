package br.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dto.ContaDTO;
import br.com.dto.SaqueDepositoDTO;
import br.com.entity.Conta;
import br.com.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@RequestMapping(value = "/salvar-conta/", method = RequestMethod.POST)
	public ResponseEntity<Conta> salvarConta(@RequestBody ContaDTO contaDTO){
		return new ResponseEntity<>(contaService.salvar(contaDTO), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/buscar-por-numeroConta/{numeroConta}")
	public ResponseEntity<ContaDTO> buscarConta(@PathVariable String numeroConta){
		return new ResponseEntity<>(contaService.buscar(numeroConta), HttpStatus.OK);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping(path = {"/{id}"})
	public void deletarConta(@PathVariable Long id) {
		contaService.excluir(id);
	}
	
	@RequestMapping(value = "/lista-Todas-conta/", method = RequestMethod.GET)
	public ResponseEntity<List<Conta>> listaConta(){
		return new ResponseEntity<>(contaService.listaDeConta(),HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Conta> atualizarConta(@PathVariable ("id") Long id,@RequestBody Conta conta){
		return new ResponseEntity<>(contaService.editar(conta, id), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/saque", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> saque(@RequestBody SaqueDepositoDTO dto){
		Conta conta = contaService.carregarContaPorNumero(dto.getAgencia(), dto.getNumeroConta());
		contaService.saque(conta,dto.getValor());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deposito", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> deposito(final @RequestBody SaqueDepositoDTO dto){
		Conta conta = contaService.carregarContaPorNumero(dto.getAgencia(), dto.getNumeroConta());
		contaService.deposito(conta.getId(),dto.getValor());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultar-saldo/{agencia}/{numeroConta}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Double> consultarSaldo(@PathVariable String agencia, @PathVariable String numeroConta){
		double saldo = contaService.consultarSaldo(agencia,numeroConta);
		return new ResponseEntity<Double>(saldo, HttpStatus.OK);
	}
	
	
}
