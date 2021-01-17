package br.com.digitalzyon.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(schema = "model", name = "servico")
public class ServicoPrestado {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@CreationTimestamp
	@Column(name = "data_cadastro", nullable = false)
	private LocalDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(name = "data_alteracao")
	private LocalDateTime dataAlteracao;
	
	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

}
