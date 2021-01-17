package br.com.digitalzyon.domain.exception;

public class EntidadeNaoCadastradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoCadastradaException(String mensagem) {
		super(mensagem);
	}
	
	public EntidadeNaoCadastradaException(String entidade, Long id) {
		super(String.format("Não existe %s com o código %d", entidade, id));
	}

}
