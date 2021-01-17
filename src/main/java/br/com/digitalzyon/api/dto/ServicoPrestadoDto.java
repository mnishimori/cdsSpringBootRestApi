package br.com.digitalzyon.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicoPrestadoDto {
	
	@NotBlank(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	
	@NotNull(message = "{campo.valor.obrigatorio}")
	private String valor;
	
	@NotNull(message = "{campo.idCliente.obrigatorio}")
	private Integer idCliente;

}
