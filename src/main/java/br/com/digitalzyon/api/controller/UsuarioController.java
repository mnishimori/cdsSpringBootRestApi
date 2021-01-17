package br.com.digitalzyon.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.digitalzyon.domain.exception.EntidadeJaCadastradaException;
import br.com.digitalzyon.domain.model.Usuario;
import br.com.digitalzyon.domain.service.UsuarioService;

//@CrossOrigin
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody @Valid Usuario usuario) {
		try {
			usuarioService.salvar(usuario);
			
		} catch (EntidadeJaCadastradaException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			
		}
	}
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioService.listar();
	}

}
