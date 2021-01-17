package br.com.digitalzyon.api.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class ApiError {
	
	private List<String> errors;
	
	public ApiError(List<String> errors) {
		this.errors = errors;
	}
	
	public ApiError(String erro) {
		this.errors = Arrays.asList(erro);
	}

}
