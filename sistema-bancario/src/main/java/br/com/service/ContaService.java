package br.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.convert.Converter;
import br.com.dto.ContaDTO;
import br.com.entity.Conta;
import br.com.enumeration.TipoConta;
import br.com.enumeration.TipoTransacao;
import br.com.exception.ErroException;
import br.com.exception.Mensagens;
import br.com.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private Converter convert;

	@Autowired
	private ExtratoService extratoService;

	public Conta salvar(ContaDTO contaDTO) {
		Conta conta = convert.convertToConta(contaDTO);
		return contaRepository.save(conta);
	}

	public ContaDTO buscar(String numeroConta) {
		Conta conta = contaRepository.findByNumeroConta(numeroConta);
		if (conta == null) {
			throw new ErroException(Mensagens.CONTA_INVALIDA);
		}
		return convert.convertToContaDTO(conta);
	}

	public void excluir(Long id) {
		Conta conta = contaRepository.getOne(id);
		if (conta == null) {
			throw new ErroException(Mensagens.CONTA_INEXISTENTE);
		}
		contaRepository.deleteById(id);
	}

	public Conta editar(Conta conta, Long id) {
		conta.setId(id);
		return contaRepository.save(conta);
	}

	public void saque(Conta conta, double valor) {
		
		if (TipoConta.CORRENTE.getTipo().equals(conta.getTipoConta())) {
			executaSaqueCorrente(conta, valor);
			extratoService.gerarExtratoParaSaque(conta, valor);
		} else {
			executaSaquePoupanca(conta, valor);
			extratoService.gerarExtratoParaSaque(conta, valor);
		}
	}

	private void executaSaqueCorrente(Conta conta, double valor) {
		conta.setSaldo(conta.getSaldo() - valor);
		conta.setDescricao(" Tipo de conta " + TipoConta.CORRENTE.getTipo() + " Tipo de transação "
				+ TipoTransacao.SAQUE + " saldo final  R$ " + conta.getSaldo());
		contaRepository.save(conta);
	}

	private void executaSaquePoupanca(Conta conta, double valor) {
		conta.setSaldo(conta.getSaldo() - valor);
		conta.setDescricao(" Tipo de conta " + TipoConta.POUPANCA.getTipo() + " Tipo de transação "
				+ TipoTransacao.SAQUE + " saldo final  R$ " + conta.getSaldo());
		contaRepository.save(conta);
	}

	public Conta carregarContaPorNumero(String agencia, String numeroConta) {
		Optional<Conta> conta = contaRepository.findByAgenciaAndNumeroConta(agencia, numeroConta);
		if (!conta.isPresent()) {
			throw new ErroException(Mensagens.CONTA_INEXISTENTE);
		}
		return conta.get();
	}

	public void transferencia(Conta contaOrigem, Conta contaDestino, double valor) {

		verificarConta(contaDestino);
		contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
		contaDestino.setSaldo(contaDestino.getSaldo() + valor);
		contaRepository.save(contaOrigem);
		contaRepository.save(contaDestino);
		extratoService.gerarExtratoParaTransferencia(valor, contaOrigem, contaDestino);
	}

	public void deposito(Long idConta, double valor) {
		Conta conta = contaRepository.getOne(idConta);
		if (TipoConta.CORRENTE.getTipo().equals(conta.getTipoConta())) {
			executaDepositoCorrente(valor, conta);
			extratoService.gerarExtratoParaDeposito(valor, conta);
		} else {
			executaDepositoPoupanca(valor, conta);
			extratoService.gerarExtratoParaDeposito(valor, conta);
		}
	}

	private void executaDepositoCorrente(double valor, Conta conta) {
		conta.setSaldo(conta.getSaldo() + valor);
		conta.setDescricao("Tipo de conta " + TipoConta.CORRENTE + " Tipo de transação " + TipoTransacao.DEPOSITO
				+ " R$ " + valor + " Saldo Final " + conta.getSaldo());
		contaRepository.save(conta);
	}

	private void executaDepositoPoupanca(double valor, Conta conta) {
		conta.setSaldo(conta.getSaldo() + valor);
		conta.setDescricao("Tipo de conta " + TipoConta.POUPANCA + " Tipo de transação " + TipoTransacao.DEPOSITO
				+ " R$ " + valor + " Saldo Final " + conta.getSaldo());
		contaRepository.save(conta);
	}

	public double consultarSaldo(String agencia, String numeroConta) {
		Conta conta = carregarContaPorNumero(agencia, numeroConta);
		return conta.getSaldo();
	}

	public void verificarConta(Conta contaDestino) {
		if (contaDestino == null) {
			throw new ErroException(Mensagens.CONTA_INVALIDA);
		}
	}

	public List<Conta> listaDeConta() {

		if (contaRepository.findAll() == null) {
			throw new ErroException(Mensagens.LISTA_DE_CONTAS_VAZIA);
		}
		return contaRepository.findAll();
	}
}
