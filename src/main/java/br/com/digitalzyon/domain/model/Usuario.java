package br.com.digitalzyon.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "model", name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@CreationTimestamp
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	private LocalDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(name = "data_atualizacao", nullable = true)
	private LocalDateTime dataAtualizacao;
	
	@Email(message = "{campo.email.invalido}")
	@NotBlank(message = "{campo.email.obrigatorio}")
	@Column(name = "login", unique = true)
	private String username;

	@NotBlank(message = "{campo.senha.obrigatorio}")
	@Min(value = 6, message = "{campo.senha.quantidade.min.caracteres}")
	@Column(name = "senha")
	private String password;

}
