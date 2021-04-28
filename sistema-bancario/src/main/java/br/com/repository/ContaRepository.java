package br.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

	Optional<Conta> findByAgenciaAndNumeroConta(String agencia, String numeroConta);
	
	public Conta findByNumeroConta(String numeroConta);
	
}
