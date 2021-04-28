package br.com.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.entity.listener.ExtratoListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "extratos")
@EntityListeners(ExtratoListener.class)
public class Extrato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDateTime dataTransacao;

	@ManyToOne
	@JoinColumn(name = "fk_conta_id", nullable = false)
	private Conta conta;

	private double valor;

	private String descricao;

	
	private String tipoTransacao;

	public Extrato(Conta conta, double valor, String descricao, String tipoTransacao) {
		super();
		this.conta = conta;
		this.valor = valor;
		this.descricao = descricao;
		this.tipoTransacao = tipoTransacao;
	}

}
