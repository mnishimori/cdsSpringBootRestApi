package br.com.digitalzyon.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.digitalzyon.domain.model.Cliente;
import br.com.digitalzyon.domain.repository.ClienteRepository;

// @CrossOrigin
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public Cliente buscar(@PathVariable Integer clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@PutMapping("/{clienteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer clienteId, @RequestBody @Valid Cliente clienteAtualizar) {
		clienteRepository.findById(clienteId).map(cliente -> {
			clienteAtualizar.setId(cliente.getId());
			clienteRepository.save(clienteAtualizar);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@DeleteMapping("/{clienteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer clienteId) {
		clienteRepository.findById(clienteId).map(cliente -> {
			clienteRepository.delete(cliente);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
}
