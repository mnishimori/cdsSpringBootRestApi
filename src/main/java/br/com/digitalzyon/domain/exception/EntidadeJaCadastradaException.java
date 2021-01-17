package br.com.digitalzyon.domain.exception;

public class EntidadeJaCadastradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeJaCadastradaException(String mensagem) {
		super(mensagem);
	}
	
	public EntidadeJaCadastradaException(String entidade, Long id) {
		super(String.format("Já existe %s cadastrado com o código %d", entidade, id));
	}

}
