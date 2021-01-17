package br.com.digitalzyon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}
	
	/*
	 * @Bean public CommandLineRunner teste(@Autowired ClienteRepository
	 * clienteRepository) { return args -> {
	 * 
	 * Cliente cliente =
	 * Cliente.builder().cpf("25288408882").nome("Marcelo Akio Nishimori").build();
	 * clienteRepository.save(cliente);
	 * 
	 * }; }
	 */

}
