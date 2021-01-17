package br.com.digitalzyon.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "model", name = "cliente")
public class Cliente {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@CreationTimestamp
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	private LocalDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(name = "data_alteracao")
	private LocalDateTime dataAlteracao;
	
	@NotBlank(message = "{campo.nome.obrigatorio}")
	@Column(nullable = false, length = 150)
	private String nome;
	
	@CPF(message = "{campo.cpf.invalido}")
	@NotNull(message = "{campo.cpf.obrigatorio}")
	@Column(nullable = false, length = 11)
	private String cpf;
	
}
