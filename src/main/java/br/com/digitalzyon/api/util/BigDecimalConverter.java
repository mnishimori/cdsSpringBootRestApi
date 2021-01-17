package br.com.digitalzyon.api.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class BigDecimalConverter {
	
	public BigDecimal convertStringToBigDecimal(String valor) {
		BigDecimal valorRetorno = null;
		
		if (valor != null) {
			String valorConvertido = valor.replace(".", "");
			valorConvertido = valorConvertido.replace(",", ".");
			valorRetorno = new BigDecimal(valorConvertido);
		}
		
		return valorRetorno;
	}

}
