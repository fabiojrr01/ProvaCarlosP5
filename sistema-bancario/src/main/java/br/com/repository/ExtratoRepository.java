package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.entity.Extrato;

@Repository
public interface ExtratoRepository extends JpaRepository<Extrato, Long>{

	@Query(value = "select * from extratos ex where ex.fk_conta_id = ?1", nativeQuery = true)
    List<Extrato> findByContaId(Long contaId);   
    
    @Query(value = "select * "
		+ " from extratos ex, contas c "
		+ " where ex.fk_conta_id = c.id "
		+ " and c.agencia = :agencia"
		+ " and c.numero_conta = :numeroConta ", nativeQuery = true )
    List<Extrato> findByAgenciaAndNumeroConta(String agencia, String numeroConta);
}
