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

import br.com.digitalzyon.api.dto.ServicoPrestadoDto;
import br.com.digitalzyon.api.util.BigDecimalConverter;
import br.com.digitalzyon.domain.model.Cliente;
import br.com.digitalzyon.domain.model.ServicoPrestado;
import br.com.digitalzyon.domain.repository.ClienteRepository;
import br.com.digitalzyon.domain.repository.ServicoPrestadoRepository;

//@CrossOrigin
@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {
	
	@Autowired
	private ServicoPrestadoRepository servicoPrestadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BigDecimalConverter bigDecimalConverter;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDto servicoPrestadoDto) {
		
		Cliente cliente = clienteRepository.findById(servicoPrestadoDto.getIdCliente())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		
		ServicoPrestado servicoPrestado = this.recuperarServicoPrestadoFromDto(servicoPrestadoDto, cliente);
		
		return servicoPrestadoRepository.save(servicoPrestado);
	}

	@GetMapping
	public List<ServicoPrestado> listar(){
		return servicoPrestadoRepository.findAll();
	}
	
	@GetMapping("/cliente-id/{clienteId}")
	public List<ServicoPrestado> buscarPorClienteId(@PathVariable Integer clienteId) {
		return servicoPrestadoRepository.findByClienteId(clienteId);
	}
	
	@GetMapping("/cliente-nome/{nome}")
	public List<ServicoPrestado> buscarPorClienteNome(@PathVariable String nome) {
		return servicoPrestadoRepository.findByClienteNomeIgnoreCaseContaining(nome);
	}
	
	@GetMapping("/{id}")
	public ServicoPrestado buscarPorId(@PathVariable Integer id) {
		return servicoPrestadoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado"));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody @Valid ServicoPrestadoDto servicoPrestadoDto) {
		Cliente cliente = clienteRepository.findById(servicoPrestadoDto.getIdCliente())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		
		ServicoPrestado servicoPrestado = this.recuperarServicoPrestadoFromDto(servicoPrestadoDto, cliente);
		
		servicoPrestadoRepository.findById(id).map(serv -> {
			servicoPrestado.setId(id);
			servicoPrestadoRepository.save(servicoPrestado);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado"));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		servicoPrestadoRepository.findById(id).map(serv -> {
			servicoPrestadoRepository.delete(serv);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado"));
	}
	
	private ServicoPrestado recuperarServicoPrestadoFromDto(ServicoPrestadoDto servicoPrestadoDto, Cliente cliente) {
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setDescricao(servicoPrestadoDto.getDescricao());
		servicoPrestado.setValor(bigDecimalConverter.convertStringToBigDecimal(servicoPrestadoDto.getValor()));
		return servicoPrestado;
	}

}
